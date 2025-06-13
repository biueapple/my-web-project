package com.example.mysite.user;

import java.util.ArrayList;
import java.util.Arrays;
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
			List<String> imgPaths = new ArrayList<>();
			for (RefundUser refundUser : path) {
				if (refundUser.getSavepath() != null) {
					imgPaths.addAll(Arrays.asList(refundUser.getSavepath().split(",")));
				}
			}
			for(String g : imgPaths) {
			System.out.println(g);
			}
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

		for (MultipartFile f : damagePhotos) {
			if (!f.isEmpty()) {
				String realPath = upload.fileUpload("D:/my-web-project/upload/", f);
				String fileName = new java.io.File(realPath).getName();

				savePath.add(fileName);
			}
		}

		if (savePath.isEmpty()) {
			model.addAttribute("message", "파일을 선택해주세요.");
			return "Refunduser/damageUpload";
		}
		String joinPath = String.join(",", savePath);
		uploadService.service(ids, joinPath); //db저장

		//model.addAttribute("savePath", savePath);

		User user = userService.search(lrc.getId());
		boolean admin = userService.isAdmin(user.getUserId());

			model.addAttribute("message", "보상 신청이 완료되었습니다.");
			return "Refunduser/showPicture";
		}
	}
