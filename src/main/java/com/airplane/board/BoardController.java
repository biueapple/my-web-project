package com.airplane.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
//	@RequestMapping("/")
//	public String home() {
//		return "home";
//	}
	
	@RequestMapping("/board")
	public String boardMain(Model model){
		model.addAttribute("list",boardService.selectAll());
		return "boardMain";
	}
	
	@RequestMapping("/boardSelectOne")
	public String boardSelectOne(@RequestParam("boardId") int boardId, Model model) {
		model.addAttribute("board",boardService.selectOne(boardId));
		return "boardSelect";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.GET)
	public String boardInsertForm(Model model) {
		Board board = new Board();
		model.addAttribute(board);
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
		model.addAttribute("list",boardService.selectAll());
		return "boardMain";
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
		model.addAttribute("list",boardService.selectAll());
		return "boardMain";
	}
}
