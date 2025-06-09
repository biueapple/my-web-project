package com.airplane.user;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String form(Model model) {
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		model.addAttribute("userRegisterRequest", userRegisterRequest);
		LinkedHashMap<String, String> genderMap = new LinkedHashMap<>();
		genderMap.put("male", "남자");    // 사용자 눈에는 "남자"
		genderMap.put("female", "여자"); // 사용자 눈에는 "여자"
		model.addAttribute("genderOptions", genderMap); // 서버에는 "male"/"female" 전송
		return "/user/registerForm";
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String submit(
			@Valid 
			@ModelAttribute("userRegisterRequest") 
			UserRegisterRequest cmdObj, 
			BindingResult bindingResult, // 검증 대상 객체 바로 다음에 위치해야 함
			Model model) {
		
		System.out.println("요청 파라미터 검증");
		if(!cmdObj.getPassword().equals(cmdObj.getPasswordConfirm())) {
			bindingResult.rejectValue(
					"passwordConfirm", "error.passwordConfirm"
					, "비밀번호가 일치하지 않습니다.");
		}
		
		
		
		if(bindingResult.hasErrors()) {
			LinkedHashMap<String, String> genderMap = new LinkedHashMap<>();
			genderMap.put("male", "남자");    // 사용자 눈에는 "남자"
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
	// UserController - showMypage 메서드 예시
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	public String showMypage(HttpSession session, Model model) {
	    Object loginObj = session.getAttribute("loginUser");
	    if (loginObj == null) {
	    	return "redirect:/login";
	    }

	    // 세션에 저장된 객체 타입에 따라 처리
	    User user = null;
	    if (loginObj instanceof User) {
	        user = (User) loginObj;
	    } else if (loginObj instanceof LoginRequestCommand) {
	        LoginRequestCommand loginCmd = (LoginRequestCommand) loginObj;
	        user = userService.search(loginCmd.getId());  // DB에서 User 조회
	    } else {
	        // 알 수 없는 타입이면 로그인 페이지로
	    	return "redirect:/login";
	    }

	    model.addAttribute("user", user);
	    return "user/myPage";
	}

	@RequestMapping(value="/myPage", method=RequestMethod.POST)
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
}