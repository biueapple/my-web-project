package com.airplane.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class NoticeBoardController {
	@Autowired
	private NoticeBoardService noticeBoardService;
	@Autowired
	private UserService userService;
	
	//noticeBaord
	@RequestMapping(value="/noticeBoardInsert", method=RequestMethod.GET)
	public String noticeBoardInsertForm(Model model,HttpSession session) {
		NoticeBoardDto noticeBoardDto = new NoticeBoardDto();
		model.addAttribute(noticeBoardDto);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/login";
		}
		User user = userService.search(lrc.getId());
		model.addAttribute("userId", user.getUserId());
		return "board/noticeBoardInsert";
	}
	
	@RequestMapping(value="/noticeBoardInsert", method=RequestMethod.POST)
	public String noticeBoardInsertSubmit(
			@Valid
			@ModelAttribute
			NoticeBoardDto noticeboardDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "board/noticeBoardInsert";
		}
		noticeBoardService.insertNoticeBoard(noticeboardDto);
		return "redirect:/board";
	}
	
	@RequestMapping("/noticeBoardSelectOne")
	public String noticeBoardSelectIdOne(@RequestParam("boardId") int boardId, Model model,HttpSession session) {
		BoardIdDto boardIdDto = noticeBoardService.noticeSelectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				model.addAttribute("admin","admin");
			}
		}
		model.addAttribute("notice","notice");
		model.addAttribute("boardIdDto",boardIdDto);
		return "board/boardSelect";
	}
	
	@RequestMapping(value="/noticeBoardUpdate", method=RequestMethod.GET)
	public String noticeBoardUpdateForm(@RequestParam("boardId")int boardId, Model model, HttpSession session) {
		BoardIdDto boardIdDto = noticeBoardService.noticeSelectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/noticeBoardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				model.addAttribute("noticeBoardDto",new NoticeBoardDto(noticeBoardService.noticeSelectOne(boardId)));
				return "board/noticeBoardUpdate";
			}else {
				return "redirect:/noticeBoardSelectOne?boardId="+boardId;
			}
		}
	}
	
	@RequestMapping(value="/noticeBoardUpdate", method=RequestMethod.POST)
	public String noticeBoardUpdateSubmit(
			@Valid
			NoticeBoardDto noticeBoardDto, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "board/noticeBoardUpdate";
		}
		noticeBoardService.noticeUpdate(noticeBoardDto);
		return "redirect:/noticeBoardSelectOne?boardId="+noticeBoardDto.getBoardId();
	}
	
	@RequestMapping("/noticeBoardDelete")
	public String noticeBoardDelete(@RequestParam("boardId")int boardId,Model model, HttpSession session) {
		BoardIdDto boardIdDto = noticeBoardService.noticeSelectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/noticeBoardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				noticeBoardService.noticeUpdateDelete(boardId);
				return "redirect:/board";
			}else {
				return "redirect:/noticeBoardSelectOne?boardId="+boardId;
			}
		}
	}
}
