package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.airplane.user.LoginRequestCommand;
import com.airplane.user.User;
import com.airplane.user.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/regist")
public class RefundController {

	@Autowired
	private RefundUserService refundUserService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String findUserByUserId(
			@Valid @ModelAttribute("userRegisterRequest") RefundUserRegisterRequest userRegisterRequest,
			HttpSession httpSession, Model model) {
		
		LoginRequestCommand lrc = (LoginRequestCommand) httpSession.getAttribute("loginUser");

		if (lrc == null) {
			return "redirect:/login";
		}
		String id = lrc.getId();
		User user = userService.search(id);

		List<Join> allList;
		boolean admin = userService.isAdmin(user.getUserId());
		if (admin) {
			model.addAttribute("admin", admin);
			allList = refundUserService.selectAll();
		}
		else 
			allList = refundUserService.selectNormal(user.getUserId());
		
		model.addAttribute("list", allList);
		return "/Refunduser/userInfo";

	}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public String refundUser(@RequestParam(value = "ids", required = false) List<Integer> ids, Model model) {
		if (ids == null) {
			model.addAttribute("refund", "환불할 내역을 선택하세요");
		} else {
			for (int idid : ids) {

				boolean update = refundUserService.updateStatus(idid);
				if (update) {
					model.addAttribute("refund", "환불이 완료되었습니다.");
				} else {
					model.addAttribute("refund", "오류가 생겼습니다.");
				}
			}
			// 환불 후 다시 사용자 찾기 페이지로 이동
			// 폼에 기본 객체랑 옵션도 다시 넣어줘야 함
			model.addAttribute("userRegisterRequest", new RefundUserRegisterRequest());

		}
		return "/Refunduser/refundResult";
	}

}
