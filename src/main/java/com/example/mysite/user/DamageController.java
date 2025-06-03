package com.example.mysite.user;

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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/damage")
public class DamageController {
	@Autowired
	UploadService uploadService;
	@Autowired
	Upload upload;

	@RequestMapping(value="/home" , method = RequestMethod.GET)
	public String damageResult() {

		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String damageClaim(HttpSession httpSession, Model model) {

		model.addAttribute("damageRequest", new DamageDto());
		return "Refunduser/damageUpload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String damageSubmit(@RequestParam("damagePhotos") List<MultipartFile> damagePhotos,
			@ModelAttribute DamageDto damageDto, HttpSession httpSession, Model model) {

		LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");
		if (lrc == null) {
			return "redirect:/login";
		}

		// 파일업로드
		for (MultipartFile f : damagePhotos) {
			if (!f.isEmpty()) {
				String path = upload.fileUpload("D:/my-web-project/upload/", f);
				uploadService.service(lrc, path);
				model.addAttribute("message", "보상신청이 완료되었습니다");
			}
			else {
				model.addAttribute("message", "파일을 선택하세요");
			}
		}
		return "Refunduser/damageResult";
	}
}
