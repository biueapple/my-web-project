package com.airplane.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.NoticeBoardMapper;

@Service
public class NoticeBoardService {
	@Autowired
	private NoticeBoardMapper notieceBoardMapper;
	
	//noticeBoard
	public int insertNoticeBoard(NoticeBoardDto noticeBoardDto) {
		return notieceBoardMapper.noticeInsert(noticeBoardDto);
	}
	
	public List<BoardIdDto> noticeSelectIdAll(){
		return notieceBoardMapper.noticeSelectIdAll();
	}
	
	public List<BoardIdDto> noticeSelectIdAllNormal(){
		return notieceBoardMapper.noticeSelectIdAllNormal();
	}
	
	public List<BoardIdDto> noticeSelectIdAllNormalImportance(int importance) {
		return notieceBoardMapper.noticeSelectIdAllNormalImportance(importance);
	}
	
	public BoardIdDto noticeSelectIdOne(int boardId) {
		return notieceBoardMapper.noticeSelectIdOne(boardId);
	}
	
	public BoardIdDto noticeSelectIdOneNormal(int boardId) {
		return notieceBoardMapper.noticeSelectIdOneNormal(boardId);
	}
	
	public NoticeBoard noticeSelectOne(int boardId) {
		return notieceBoardMapper.noticeSelectOne(boardId);
	}
	
	public int noticeUpdate(NoticeBoardDto noticeBoardDto) {
		return notieceBoardMapper.noticeUpdate(noticeBoardDto);
	}
	
	public int noticeUpdateDelete(int boardId) {
		return notieceBoardMapper.noticeUpdateDelete(boardId);
	}
	
	public int noticeDelete(int boardId) {
		return notieceBoardMapper.noticeDelete(boardId);
	}
}
