package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.airplane.user.User;

@Controller
public class DamageClaimController {
	@Autowired
	DamageService damageService;
	@Autowired
	private RefundUserService userService;
	
	User user;
	ModelAndView modelAndView = new ModelAndView();
	
	MultipartHttpServletRequest mphsr =(MultipartHttpServletRequest)request; //request에 담기는 정보 파일객체로 만들어줌
	List<MultipartFile> files=mphsr.getFiles("filename");//파일 여러개
	List<String> fileNameList = new ArrayList<String>(); //디비에 전송하는 객체
	
	user.setUserId((String)request.getSession().getServletContext().getRealPath("/upload"));
	//회원, session에서 아이디 구하고 datavo 객체에 있는 아이디 setter
	
	
}
