package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.airplane.plane.Plane;
import com.airplane.user.LoginRequestCommand;
import com.airplane.user.User;
import com.airplane.user.UserService;
import com.example.airport.AirinfoDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/regist")
public class RefundController {

	@Autowired
	private RefundUserService userService;
	@Autowired
	private UserService userService2;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public String form(Model model) {
//		System.out.println("검사");
//		RefundUserRegisterRequest userRegisterRequest = new RefundUserRegisterRequest();
//		model.addAttribute("userRegisterRequest", userRegisterRequest);
//		model.addAttribute("genderOptions", List.of("남자", "여자"));
//		model.addAttribute("countryOptions", List.of("한국", "미국", "일본", "중국", "독일"));
//		return "/Refunduser/findUser";
//	}

	@RequestMapping(method = RequestMethod.GET)
	public String findUserByUserId(
			@Valid @ModelAttribute("userRegisterRequest") RefundUserRegisterRequest userRegisterRequest,
			HttpSession httpSession, Model model) {
		//Integer userId = userRegisterRequest.getUserId();
		LoginRequestCommand lrc = (LoginRequestCommand)httpSession.getAttribute("loginUser");
		
		if(lrc==null) {
			return "redirect:/login";
		}
		String id = lrc.getId();
		
		User user = userService2.search(id);

		    // userId로 환불 사용자 정보 조회
		    List<RefundUser> findUsers = userService.findByName(user.getUserId());
		    if (findUsers == null || findUsers.isEmpty()) {
		        model.addAttribute("message", "조회된 회원 정보가 없습니다.");
		        return "/Refunduser/userInfo";
		    }
		    for(RefundUser f : findUsers) {
		    	f.setId(id);
		    }
		    model.addAttribute("list", findUsers);
		    model.addAttribute("user", user);
		    return "/Refunduser/userInfo";
		}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public String refundUser(@RequestParam("id") int userId, Model model) {
		boolean deleted = userService.deleteUserByUserId(userId);

		if (deleted) {
			model.addAttribute("refund", "환불이 완료되었습니다.");
		} else {
			model.addAttribute("refund", "존재하지 않는 회원입니다.");
		}

//	    // 환불 후 다시 사용자 찾기 페이지로 이동
//	    // 폼에 기본 객체랑 옵션도 다시 넣어줘야 함
		model.addAttribute("userRegisterRequest", new RefundUserRegisterRequest());
		model.addAttribute("genderOptions", List.of("남자", "여자"));
		model.addAttribute("countryOptions", List.of("한국", "미국", "일본", "중국", "독일"));

		return "/Refunduser/refundResult";
	}
}
