package com.airplane.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	private UserService userService;
	
//	@RequestMapping("/")
//	public String home() {
//		return "home";
//	}
	
	@RequestMapping("/board")
	public String boardMain(Model model){
		List<BoardIdDto> list = boardService.selectIdAll();
		model.addAttribute("list",list);
		return "boardMain";
	}
	
	@RequestMapping("/boardSelectOne")
	public String boardSelectOne(@RequestParam("boardId") int boardId, Model model, HttpSession session) {
		Board board = boardService.selectOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		User user = userService.search(lrc.getId());
		model.addAttribute("board",board);
		model.addAttribute("id",user.getId());
		return "boardSelect";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.GET)
	public String boardInsertForm(Model model,HttpSession session) {
		Board board = new Board();
		model.addAttribute(board);
		LoginRequestCommand loginRequestCommand = (LoginRequestCommand)session.getAttribute("loginUser");
		if(loginRequestCommand==null) {
			return "redirect:/login";
		}
		User user = userService.search(loginRequestCommand.getId());
		model.addAttribute("userId", user.getUserId());
		return "boardInsert";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsertSubmit(
			@Valid
			Board board, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "boardInsert";
		}
		BoardDto boardDto = new BoardDto(board);
		boardService.insertBoard(boardDto);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.GET)
	public String boardUpdateForm(@RequestParam("boardId")int boardId,Model model) {
		Board board = new Board();
		model.addAttribute(board);
		model.addAttribute("boardBefore",boardService.selectOne(boardId));
		return "boardUpdate";
	}
	
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdateSubmit(
			@Valid
			Board board, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "boardUpdate";
		}
		boardService.update(board);
		model.addAttribute(board.getBoardId());
		return "boardSelect"; 
	}
	
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("boardId")int boardId,Model model) {
		boardService.delete(boardId);
		return "redirect:/board";
	}
}
