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
	
	@RequestMapping("/board")
	public String boardMain(Model model){
		List<BoardIdDto> list = boardService.selectIdAll();
		model.addAttribute("list",list);
		return "boardMain";
	}
	
	@RequestMapping("/boardSelectOne")
	public String boardSelectIdOne(@RequestParam("boardId") int boardId, Model model, HttpSession session) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
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
	public String boardUpdateForm(@RequestParam("boardId")int boardId, Model model, HttpSession session, RedirectAttributes redirectAttrs) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			redirectAttrs.addFlashAttribute("userNotMatchError", "작성자만 수정할 수 있습니다.");
			return "redirect:/boardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()) {
				model.addAttribute("board",boardService.selectOne(boardId));
				return "boardUpdate";
			}else {
				redirectAttrs.addFlashAttribute("userNotMatchError", "작성자만 수정할 수 있습니다.");
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
	public String boardDelete(@RequestParam("boardId")int boardId,Model model, HttpSession session, RedirectAttributes redirectAttrs) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			redirectAttrs.addFlashAttribute("userNotMatchError", "작성자만 삭제할 수 있습니다.");
			return "redirect:/boardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()) {
				boardService.delete(boardId);
				return "redirect:/board";
			}else {
				redirectAttrs.addFlashAttribute("userNotMatchError", "작성자만 삭제할 수 있습니다.");
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
}
