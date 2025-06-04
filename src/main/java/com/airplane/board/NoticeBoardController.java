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
	//공지 작성 페이지
	@RequestMapping(value="/noticeBoardInsert", method=RequestMethod.GET)
	public String noticeBoardInsertForm(Model model,HttpSession session) {
		//공지 작성 페이지에 객체 보내기
		NoticeBoardDto noticeBoardDto = new NoticeBoardDto();
		model.addAttribute(noticeBoardDto);
		//계정 검증(관리자인지)
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				//작성된 공지에 userId 값 넣어주기위해 같이 보내기
				model.addAttribute("userId", user.getUserId());
				return "board/noticeBoardInsert";
			}
			return "redirect:/board";
		}
		return "redirect:/board";
	}
	
	//공지 작성 확인 페이지(등록 성공시 메인 페이지로)
	@RequestMapping(value="/noticeBoardInsert", method=RequestMethod.POST)
	public String noticeBoardInsertSubmit(
			//오류메세지 띄우기 위해 검증
			@Valid
			NoticeBoardDto noticeboardDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//오류 있을시 다시 작성페이지로
			return "board/noticeBoardInsert";
		}
		//작성된 공지 DB에 추가
		noticeBoardService.insertNoticeBoard(noticeboardDto);
		return "redirect:/board";
	}
	
	//공지 상세보기 페이지
	@RequestMapping("/noticeBoardSelectOne")
	public String noticeBoardSelectIdOne(@RequestParam("boardId") int boardId, Model model,HttpSession session) {
		BoardIdDto boardIdDto = noticeBoardService.noticeSelectIdOne(boardId);
		if(boardIdDto!=null) {
			LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
			if(lrc!=null) {
				User user = userService.search(lrc.getId());
				if(boardIdDto.getState().equals("삭제")) {
					if(userService.isAdmin(user.getUserId())) {
						model.addAttribute("admin","admin");
						model.addAttribute("notice","notice");
						model.addAttribute("boardIdDto",boardIdDto);
						return "board/boardSelect";
					}else {
						return "redirect:/board";
					}
				}else {
					if(userService.isAdmin(user.getUserId())) {
						model.addAttribute("admin","admin");
					}
					model.addAttribute("notice","notice");
					model.addAttribute("boardIdDto",boardIdDto);
					return "board/boardSelect";
				}
			}else {
				if(boardIdDto.getState().equals("삭제")) {
					return "redirect:/board";
				}else {
					model.addAttribute("notice","notice");
					model.addAttribute("boardIdDto",boardIdDto);
					return "board/boardSelect";
				}
			}
		}else {
			return "redirect:/board";
		}
		
	}
	
	//공지 수정 페이지
	@RequestMapping(value="/noticeBoardUpdate", method=RequestMethod.GET)
	public String noticeBoardUpdateForm(@RequestParam("boardId")int boardId, Model model, HttpSession session) {
		//계정 검증
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/noticeBoardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				//기존 내용 보여주기 위해 공지내용 찾아오기
				model.addAttribute("noticeBoardDto",new NoticeBoardDto(noticeBoardService.noticeSelectOne(boardId)));
				return "board/noticeBoardUpdate";
			}else {
				return "redirect:/noticeBoardSelectOne?boardId="+boardId;
			}
		}
	}
	
	//공지 수정 확인 페이지
	@RequestMapping(value="/noticeBoardUpdate", method=RequestMethod.POST)
	public String noticeBoardUpdateSubmit(
			//오류 검증
			@Valid
			NoticeBoardDto noticeBoardDto, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			return "board/noticeBoardUpdate";
		}
		//수정된 공지 DB 에 저장
		noticeBoardService.noticeUpdate(noticeBoardDto);
		return "redirect:/noticeBoardSelectOne?boardId="+noticeBoardDto.getBoardId();
	}
	
	//공지 삭제(실제 삭제아닌 DB state 항목 삭제로 변경)
	@RequestMapping("/noticeBoardDelete")
	public String noticeBoardDelete(@RequestParam("boardId")int boardId,Model model, HttpSession session) {
		//계정 검증
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/noticeBoardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				//관리자라면 삭제
				noticeBoardService.noticeUpdateDelete(boardId);
				return "redirect:/board";
			}else {
				return "redirect:/noticeBoardSelectOne?boardId="+boardId;
			}
		}
	}
}
