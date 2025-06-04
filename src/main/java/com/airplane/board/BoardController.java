package com.airplane.board;

import java.util.List;

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
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private NoticeBoardService noticeBoardService;
	@Autowired
	private UserService userService;
	
	//board
	@RequestMapping("/board")
	public String boardMain(Model model, HttpSession session){
		List<BoardIdDto> boardList = boardService.selectIdAllNormal();
		List<BoardIdDto> noticeBoardList = noticeBoardService.noticeSelectIdAllNormalImportance(2);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				boardList = boardService.selectIdAll();
				noticeBoardList = noticeBoardService.noticeSelectIdAll();
				model.addAttribute("admin","admin");
			}
		}
		model.addAttribute("boardList",boardList);
		model.addAttribute("noticeBoardList",noticeBoardList);
		return "board/boardMain";
	}
	
	@RequestMapping("/boardSelectOne")
	public String boardSelectIdOne(@RequestParam("boardId") int boardId, Model model,HttpSession session) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				model.addAttribute("matchUser","matchUser");
			}
		}
		model.addAttribute("boardIdDto",boardIdDto);
		return "board/boardSelect";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.GET)
	public String boardInsertForm(Model model,HttpSession session) {
		BoardDto boardDto = new BoardDto();
		model.addAttribute(boardDto);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/login";
		}
		User user = userService.search(lrc.getId());
		model.addAttribute("userId", user.getUserId());
		return "board/boardInsert";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsertSubmit(
			@Valid
			@ModelAttribute
			BoardDto boardDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "board/boardInsert";
		}
		boardService.insertBoard(boardDto);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.GET)
	public String boardUpdateForm(@RequestParam("boardId")int boardId, Model model, HttpSession session) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/boardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				model.addAttribute("boardDto",new BoardDto(boardService.selectOne(boardId)));
				return "board/boardUpdate";
			}else {
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdateSubmit(
			@Valid
			BoardDto boardDto, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "board/boardUpdate";
		}
		boardService.update(boardDto);
		return "redirect:/boardSelectOne?boardId="+boardDto.getBoardId();
	}
	
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("boardId")int boardId,Model model, HttpSession session) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/boardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				boardService.updateDelete(boardId);
				return "redirect:/board";
			}else {
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
}
