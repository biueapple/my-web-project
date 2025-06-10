package com.airplane.user;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String form(Model model) {
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		model.addAttribute("userRegisterRequest", userRegisterRequest);
		LinkedHashMap<String, String> genderMap = new LinkedHashMap<>();
		genderMap.put("male", "남자"); // 사용자 눈에는 "남자"
		genderMap.put("female", "여자"); // 사용자 눈에는 "여자"
		model.addAttribute("genderOptions", genderMap); // 서버에는 "male"/"female" 전송
		return "/user/registerForm";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("userRegisterRequest") UserRegisterRequest cmdObj,
			BindingResult bindingResult, // 검증 대상 객체 바로 다음에 위치해야 함
			Model model) {

		System.out.println("요청 파라미터 검증");
		if (!cmdObj.getPassword().equals(cmdObj.getPasswordConfirm())) {
			bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm", "비밀번호가 일치하지 않습니다.");
		}

		System.out.println("회원 중복 아이디 가입 검증");
		if (userService.isDuplicatedId(cmdObj.getId())) {
			bindingResult.rejectValue("id", "error.id", "이미 존재하는 아이디입니다.");
		}

		if (bindingResult.hasErrors()) {
			LinkedHashMap<String, String> genderMap = new LinkedHashMap<>();
			genderMap.put("male", "남자"); // 사용자 눈에는 "남자"
			genderMap.put("female", "여자"); // 사용자 눈에는 "여자"
			model.addAttribute("genderOptions", genderMap); // 서버에는 "male"/"female" 전송
			return "/user/registerForm";
		}

		System.out.println("사용자 서비스 호출");
		userService.regist(cmdObj);

		System.out.println("결과 모델에 담기");
		model.addAttribute("result", "회원가입 성공");

		return "/user/registResult";
	}

	// 마이페이지 조회 (GET)
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String showMypage(HttpSession session, Model model) {
		Object loginObj = session.getAttribute("loginUser");
		if (loginObj == null) {
			return "redirect:/login";
		}

		User user = null;
		if (loginObj instanceof User) {
			user = (User) loginObj;
		} else if (loginObj instanceof LoginRequestCommand) {
			LoginRequestCommand loginCmd = (LoginRequestCommand) loginObj;
			user = userService.search(loginCmd.getId()); 
		} else {
			// 알 수 없는 타입이면 로그인 페이지로
			return "redirect:/login";
		}

		model.addAttribute("user", user);
		return "user/myPage";
	}

	@RequestMapping(value = "/myPage", method = RequestMethod.POST)
	public String updateMypage(@ModelAttribute("user") UserDto userDto, HttpSession session) {
		Object loginObj = session.getAttribute("loginUser");
		if (loginObj == null) {
			return "redirect:/login";
		}

		User user = null;
		if (loginObj instanceof User) {
			user = (User) loginObj;
		} else if (loginObj instanceof LoginRequestCommand) {
			LoginRequestCommand loginCmd = (LoginRequestCommand) loginObj;
			user = userService.search(loginCmd.getId());
		} else {
			return "redirect:/login";
		}

		userDto.setUserId(user.getUserId());
		userService.updateUserInfo(userDto);

		User updatedUser = userService.findUserById(user.getUserId());
		session.setAttribute("loginUser", updatedUser);

		return "user/myPage";
	}

	// 회원정보수정 비밀번호 변경
	@GetMapping("/changePassword")
	public String showChangePasswordForm(Model model) {
		model.addAttribute("passwordChangeForm", new PasswordChangeForm());
		return "user/changePasswordForm";
	}

	@PostMapping("/changePassword")
	public String changePassword(@Valid @ModelAttribute("passwordChangeForm") PasswordChangeForm form,
			BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {

		LoginRequestCommand loginObj = (LoginRequestCommand)session.getAttribute("loginUser");
		if (loginObj == null) {
		    return "redirect:/login";
		}
		
		User user = userService.search(loginObj.getId()); 
		if(user == null)
			return "redirect:/login";
			
		int userId = user.getUserId();		
		
		if (!userService.changePassword(userId, form.getCurrentPassword(), form.getNewPassword(),
				form.getNewPasswordConfirm())) {
			result.reject("error.changePw");
			return "user/changePasswordForm";
		}

		redirectAttrs.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
		return "redirect:/myPage";
	}

}
