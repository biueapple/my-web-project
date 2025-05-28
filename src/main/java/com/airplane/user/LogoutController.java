package com.airplane.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

	@RequestMapping(value = "/user/logout", method = RequestMethod.GET) //로그아웃 요청을 처리할 URL 경로를 지정
	public String logout(HttpSession session) { //세션정보가져오기
		System.out.println("로그아웃 동작");
		session.invalidate();	//세션무효화
		return "redirect:/";	//메인페이지로 이동
	}
}


