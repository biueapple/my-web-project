package com.example.mysite.user;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.airplane.user.LoginRequestCommand;
import com.airplane.user.User;
import com.airplane.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/damage")
public class DamageController {
	@Autowired
	DamageService damageService;

	@RequestMapping(method = RequestMethod.GET)
	public String damageClaim(HttpSession httpSession, Model model) {

		model.addAttribute("damageRequest", new DamageDto());
		return "/RefundUser/damageUpload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String damageSubmit(@RequestParam("damagePhotos") List<MultipartFile> damagePhotos,
			@ModelAttribute DamageDto damageDto, HttpSession httpSession,
			Model model) {
		LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");
		if (lrc == null) {
			return "redirect:/login";
		}
		String id = lrc.getId();
		// User user = userService.search(id);

		// 파일업로드
		for (MultipartFile f : damagePhotos) {
			if (!f.isEmpty()) {
				
				String savePath = "C:/cho/";
				String fileName = f.getOriginalFilename(); // 업로드된 파일 이름
				File dest = new File(savePath + fileName);
				try {
					if (!uploadDir.exists()) {
  					uploadDir.mkdirs(); // 폴더가 없으면 자동 생성
				}
					f.transferTo(new File(dest));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "파일 업로드 중 오류가 발생하였습니다");
					return "/RefundUser/damageResult";
				}
			}
		}
		damageDto.setId(lrc.getId());
		damageService.registerRequest(damageDto);
		model.addAttribute("message", "보상신청이 완료되었습니다");
		return "/RefundUser/damageResult";
	}

}
