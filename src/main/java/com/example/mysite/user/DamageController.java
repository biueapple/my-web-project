package com.example.mysite.user;

import java.util.ArrayList;
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
	@Autowired
	RefundUserService refundUserService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
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
			@RequestParam(value = "ids") List<Integer> ids, @ModelAttribute DamageDto damageDto,
			HttpSession httpSession, Model model) {

		LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");
		if (lrc == null) {
			return "redirect:/login";
		}

		List<String> savedPath = new ArrayList<>();
		// 파일업로드
		
		for (int id : ids) {

			boolean update = refundUserService.updateStatus(id);
			if (update) {
				for (MultipartFile f : damagePhotos) {
					if (!f.isEmpty()) {
						String path = upload.fileUpload("D:/my-web-project/upload/", f);
						uploadService.service(ids, path);
						savedPath.add("/upload/" + f.getOriginalFilename());
					}
				}
				if (savedPath.isEmpty()) {
					model.addAttribute("message", "파일을 선택하세요");
					return "Refunduser/damageUpload";
				} else {
					model.addAttribute("message", "보상신청이 완료되었습니다");
					model.addAttribute("savepath", savedPath);
				}

			} else {
				model.addAttribute("message", "오류가 생겼습니다.");
			}
		}
		return "Refunduser/damageResult";

	}
}
