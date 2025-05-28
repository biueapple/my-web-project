package com.airplane.user;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/regist")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(Model model) {
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		model.addAttribute("userRegisterRequest", userRegisterRequest);
		LinkedHashMap<String, String> genderMap = new LinkedHashMap<>();
		genderMap.put("male", "남자");    // 사용자 눈에는 "남자"
		genderMap.put("female", "여자"); // 사용자 눈에는 "여자"
		model.addAttribute("genderOptions", genderMap); // 서버에는 "male"/"female" 전송
		return "/user/registerForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
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
	
}