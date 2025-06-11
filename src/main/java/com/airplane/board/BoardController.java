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
	//게시판 메인페이지
	@RequestMapping("/board")
	public String boardMain(Model model, HttpSession session){
		//공지와 일반글 리스트로 가져오기
		List<BoardIdDto> boardList = boardService.selectIdAllNormal();
		List<BoardIdDto> noticeBoardList = noticeBoardService.noticeSelectIdAllNormalImportance(2);
		//관리자인지 확인하기
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())) {
				//관리자라면 삭제된 게시글도 볼수있게설정
				boardList = boardService.selectIdAll();
				noticeBoardList = noticeBoardService.noticeSelectIdAll();
				model.addAttribute("admin","admin");
			}
		}
		//메인 페이지에 보내주기
		model.addAttribute("boardList",boardList);
		model.addAttribute("noticeBoardList",noticeBoardList);
		return "board/boardMain";
	}
	
	//상세보기 페이지
	@RequestMapping("/boardSelectOne")
	public String boardSelectIdOne(@RequestParam("boardId") int boardId, Model model,HttpSession session) {
		//게시글하나 가져오기
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		//get 방식으로 받기 때문에 주소로 삭제된 게시글이나 비정상적이 접근 막기위해 검증
		if(boardIdDto!=null) {
			LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
			if(lrc!=null) {
				User user = userService.search(lrc.getId());
				if(boardIdDto.getState().equals("삭제")) {
					if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
						model.addAttribute("matchUser","matchUser");
						model.addAttribute("boardIdDto",boardIdDto);
						return "board/boardSelect";
					}else {
						return "redirect:/board";
					}
				}else {
					if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
						model.addAttribute("matchUser","matchUser");
					}
					model.addAttribute("boardIdDto",boardIdDto);
					return "board/boardSelect";
				}
			}else {
				if(boardIdDto.getState().equals("삭제")) {
					return "redirect:/board";
				}else {
					model.addAttribute("boardIdDto",boardIdDto);
					return "board/boardSelect";
				}
			}
		}else {
			return "redirect:/board";
		}
		
	}
	
	//글 작성 페이지
	@RequestMapping(value="/boardInsert", method=RequestMethod.GET)
	public String boardInsertForm(Model model,HttpSession session) {
		//작성페이지에 객체 보내기
		BoardDto boardDto = new BoardDto();
		model.addAttribute(boardDto);
		//로그인이 안되어있다면 로그인페이지로 보내기
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/login";
		}
		//작성된 글에 userId 값 넣어주기위해 같이보내기
		User user = userService.search(lrc.getId());
		model.addAttribute("userId", user.getUserId());
		return "board/boardInsert";
	}
	
	//글 작성 확인 페이지(등록성공시 게시판메인 페이지로 이동)
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsertSubmit(
			//오류메세지 띄우기 위해 검증
			@Valid
			BoardDto boardDto, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//오류 있을시 다시 작성페이지로
			return "board/boardInsert";
		}
		//작성내용 DB에 추가
		boardService.insertBoard(boardDto);
		return "redirect:/board";
	}
	
	//게시글 수정 페이지
	@RequestMapping(value="/boardUpdate", method=RequestMethod.GET)
	public String boardUpdateForm(@RequestParam("boardId")int boardId, Model model, HttpSession session) {
		//작성자이거나 관리자만 수정할수있게 확인
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			//로그인이 안되어있다면 다시 상세보기 페이지로
			return "redirect:/boardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				//작성자이거나 관리자면 수정 페이지로
				model.addAttribute("boardDto",new BoardDto(boardService.selectOne(boardId)));
				return "board/boardUpdate";
			}else {
				//로그인은 되어있지만 작성자또는 관리자가 아니면 다시 상세보기 페이지로
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
	
	//글 수정 확인 페이지(수정성공시 상세보기 페이지로 이동)
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdateSubmit(
			//오류메세지 띄우기 위해 검증
			@Valid
			BoardDto boardDto, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			//오류 있을시 다시 수정페이지로
			return "board/boardUpdate";
		}
		//수정내용 DB에 업데이트
		boardService.update(boardDto);
		return "redirect:/boardSelectOne?boardId="+boardDto.getBoardId();
	}
	
	//게시글 삭제 기능(실제 삭제아닌 DB state 항목 삭제로 변경)
	@RequestMapping("/boardDelete")
	public String boardDelete(@RequestParam("boardId")int boardId,Model model, HttpSession session) {
		BoardIdDto boardIdDto = boardService.selectIdOne(boardId);
		//계정 검증(관리자이거나 작성자)
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/boardSelectOne?boardId="+boardId;
		}else {
			User user = userService.search(lrc.getId());
			if(user.getUserId()==boardIdDto.getUserId()||userService.isAdmin(user.getUserId())) {
				//관리자또는 작성자라면 삭제 기능실행
				boardService.updateDelete(boardId);
				//삭제 완료 후 다시 메인페이지로
				return "redirect:/board";
			}else {
				return "redirect:/boardSelectOne?boardId="+boardId;
			}
		}
	}
	
	//게시글 검색기능
	@RequestMapping(value="/searchBoard", method=RequestMethod.POST)
	public String searchBoard(
			//검색타입 받아오기
			@RequestParam("searchType")String searchType,
			//검색어 받아오기
			@RequestParam("keyword")String keyword,
			Model model
			) {
		//공지는 항상띄우기 위해 리스트 받아오기
		List<BoardIdDto> noticeBoardList = noticeBoardService.noticeSelectIdAllNormalImportance(2);
		//검색된 게시글 리스트 받아오기
		List<BoardIdDto> searchBoardList = boardService.searchBoard(keyword, searchType);
		//리스트 두개 넣어주기
		model.addAttribute("noticeList", noticeBoardList);
		model.addAttribute("list", searchBoardList);
		return "board/boardSearch";
	}
}
