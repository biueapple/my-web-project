package com.airplane.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(
			@ModelAttribute LoginRequestCommand loginRequestCommand,
			@CookieValue(value = "remember", required = false) Cookie cookie) {
		
		if(cookie != null) {
			loginRequestCommand.setId(cookie.getValue());
			loginRequestCommand.setRemember(true);
		}
		return "user/loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(
			@Valid LoginRequestCommand loginRequestCommand,
			BindingResult bindingResult,
			HttpSession session,
			HttpServletResponse response) {
		System.out.println("로그인 요청 커맨드 객체 검증");
		
		if(bindingResult.hasErrors()) {
			return "user/loginForm";
		}
		
		boolean result = userService.login(loginRequestCommand);
		
		if(result) {
			System.out.println("로그인 성공");
			session.setAttribute("loginUser", loginRequestCommand);
			
			Cookie cookie = new Cookie(
					"remember", loginRequestCommand.getId());
			cookie.setPath("/");
			if(loginRequestCommand.isRemember()) {
				cookie.setMaxAge(60*60*24); // 60*60*24*30*3
			}else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			bindingResult.reject("error.login");
			return "user/loginForm";
		}
		
	}
	
//	@ExceptionHandler(EmptyResultDataAccessException.class)
//	public String handlerException1(EmptyResultDataAccessException e, Model model) {
//		model.addAttribute("errorMessage", "회원 정보 없음");
//		return "error/userError";
//	}
}





