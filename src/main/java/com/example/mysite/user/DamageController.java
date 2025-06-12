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
import com.airplane.user.User;
import com.airplane.user.UserService;

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

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public String damageClaim(@RequestParam(value = "ids", required = false) List<Integer> ids, HttpSession httpSession,
			Model model) {
		if (ids == null || ids.isEmpty()) {
			model.addAttribute("message", "선택된 예약이 없습니다.");
			return "redirect:/"; // 또는 에러페이지
		}
		LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");
		if (lrc == null) {
			return "redirect:/login";
		}
		httpSession.setAttribute("ticketId", ids);
		model.addAttribute("damageRequest", new DamageDto());

		User user = userService.search(lrc.getId());
		boolean admin = userService.isAdmin(user.getUserId());
		if (!admin)
			return "Refunduser/damageUpload";
		else {
			List<RefundUser> path = refundUserService.findSavepath(ids);
			model.addAttribute("path", path);
			return "Refunduser/adminPicture";
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String damageSubmit(@RequestParam("damagePhotos") List<MultipartFile> damagePhotos,
			@ModelAttribute DamageDto damageDto, HttpSession httpSession, Model model) {

		LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");
		if (lrc == null) {
			return "redirect:/login";
		}

		List<Integer> ids = (List<Integer>) httpSession.getAttribute("ticketId");

		List<String> savePath = new ArrayList<>();
		String realPath = null;
		for (int id : ids) {
			boolean updated = true;
			if (updated) {
				for (MultipartFile f : damagePhotos) {
					if (!f.isEmpty()) {
						realPath = upload.fileUpload("D:/my-web-project/upload/", f);
						uploadService.service(ids, realPath);
						savePath.add("/upload/" + f.getName());
					}
				}
			}
		}

		if (savePath.isEmpty()) {
			model.addAttribute("message", "파일을 선택해주세요.");
			return "Refunduser/damageUpload";
		}
		model.addAttribute("savePath", "/upload/" + realPath);

		User user = userService.search(lrc.getId());
		boolean admin = userService.isAdmin(user.getUserId());

		if (admin) {
			List<RefundUser> path = refundUserService.findSavepath(ids);
			model.addAttribute("path", path);
			return "Refunduser/adminPicture";
		} else {
			model.addAttribute("message", "보상 신청이 완료되었습니다.");
			return "Refunduser/showPicture";
		}
	}
}