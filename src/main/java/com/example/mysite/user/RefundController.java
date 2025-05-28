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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/regist")
public class RefundController {

	@Autowired
	private RefundUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String form(Model model) {
		System.out.println("검사");
		RefundUserRegisterRequest userRegisterRequest = new RefundUserRegisterRequest();
		model.addAttribute("userRegisterRequest", userRegisterRequest);
		model.addAttribute("genderOptions", List.of("남자", "여자"));
		model.addAttribute("countryOptions", List.of("한국", "미국", "일본", "중국", "독일"));
		return "/Refunduser/findUser";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String findUserByName(@Valid @ModelAttribute("userRegisterRequest") RefundUserRegisterRequest userRegisterRequest,
			BindingResult result,
			Model model) {
		
	    if (result.hasErrors()) {
	        // 검증 실패하면 다시 폼으로, 오류 메시지 뜸!
	        model.addAttribute("genderOptions", List.of("남자", "여자"));
	        model.addAttribute("countryOptions", List.of("한국", "미국", "일본", "중국", "독일"));
	        return "/Refunduser/findUser";
	    }
		String name = userRegisterRequest.getName();
		List <RefundUser> user = userService.findByName(name);
		if (user == null) {
			model.addAttribute("message", "예약내역이 없습니다");
			model.addAttribute("userRegisterRequest", userRegisterRequest);
			model.addAttribute("genderOptions", List.of("남자", "여자"));
			model.addAttribute("countryOptions", List.of("한국", "미국", "일본", "중국", "독일"));
			return "/Refunduser/findUser";

		}
		System.out.println(user.size());
		model.addAttribute("list", user);
		return "/Refunduser/userInfo";
	}
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public String refundUser(@RequestParam("name") String username, Model model) {
	    boolean deleted = userService.deleteUserByName(username);
	    
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
