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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private UserService userService;
	
//	@RequestMapping("/")
//	public String home() {
//		return "home";
//	}
	
	//board
	@RequestMapping("/board")
	public String boardMain(Model model, HttpSession session){
		List<BoardIdDto> boardList = boardService.selectIdAll();
		List<BoardIdDto> noticeBoardList = boardService.noticeSelectIdAll();
		model.addAttribute("boardList",boardList);
		model.addAttribute("noticeBoardList",noticeBoardList);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				model.addAttribute("admin","admin");
			}
		}
		return "boardMain";
	}
	
	@RequestMapping("/boardSelectOne")
	public String boardSelectIdOne(@RequestParam("boardId") int boardId, Model model,HttpSession session) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()) {
				model.addAttribute("matchUser","matchUser");
			}
		}
		model.addAttribute("boardIdDto",boardIdDto);
		return "boardSelect";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.GET)
	public String boardInsertForm(Model model,HttpSession session) {
		Board board = new Board();
		model.addAttribute(board);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/login";
		}
		User user = userService.search(lrc.getId());
		model.addAttribute("userId", user.getUserId());
		return "boardInsert";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsertSubmit(
			@Valid
			@ModelAttribute
			Board board, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "boardInsert";
		}
		BoardDto boardDto = new BoardDto(board);
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
				model.addAttribute("board",boardService.selectOne(boardId));
				return "boardUpdate";
			}else {
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdateSubmit(
			@Valid
			Board board, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "boardUpdate";
		}
		boardService.update(board);
		return "redirect:/boardSelectOne?boardId="+board.getBoardId();
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
				boardService.delete(boardId);
				return "redirect:/board";
			}else {
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
	
	//noticeBaord
	@RequestMapping(value="/noticeBoardInsert", method=RequestMethod.GET)
	public String noticeBoardInsertForm(Model model,HttpSession session) {
		Board board = new Board();
		model.addAttribute(board);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/login";
		}
		User user = userService.search(lrc.getId());
		model.addAttribute("userId", user.getUserId());
		return "boardInsert";
	}
	
	@RequestMapping(value="/noticeBoardInsert", method=RequestMethod.POST)
	public String noticeBoardInsertSubmit(
			@Valid
			@ModelAttribute
			Board board, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "boardInsert";
		}
		BoardDto boardDto = new BoardDto(board);
		boardService.insertNoticeBoard(boardDto);
		return "redirect:/board";
	}
	
	@RequestMapping("/noticeBoardSelectOne")
	public String noticeBoardSelectIdOne(@RequestParam("boardId") int boardId, Model model,HttpSession session) {
		BoardIdDto boardIdDto = boardService.noticeSelectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				model.addAttribute("admin","admin");
			}
		}
		model.addAttribute("notice","notice");
		model.addAttribute("boardIdDto",boardIdDto);
		return "boardSelect";
	}
	
	@RequestMapping(value="/noticeBoardUpdate", method=RequestMethod.GET)
	public String noticeBoardUpdateForm(@RequestParam("boardId")int boardId, Model model, HttpSession session) {
		BoardIdDto boardIdDto = boardService.noticeSelectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/noticeBoardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				model.addAttribute("board",boardService.noticeSelectOne(boardId));
				return "boardUpdate";
			}else {
				return "redirect:/noticeBoardSelectOne?boardId="+boardId;
			}
		}
	}
	
	@RequestMapping(value="/noticeBoardUpdate", method=RequestMethod.POST)
	public String noticeBoardUpdateSubmit(
			@Valid
			Board board, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "boardUpdate";
		}
		boardService.noticeUpdate(board);
		return "redirect:/noticeBoardSelectOne?boardId="+board.getBoardId();
	}
	
	@RequestMapping("/noticeBoardDelete")
	public String noticeBoardDelete(@RequestParam("boardId")int boardId,Model model, HttpSession session) {
		BoardIdDto boardIdDto = boardService.noticeSelectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/noticeBoardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				boardService.noticeDelete(boardId);
				return "redirect:/board";
			}else {
				return "redirect:/noticeBoardSelectOne?boardId="+boardId;
			}
		}
	}
}
