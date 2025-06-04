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
	//insert
	public int insertNoticeBoard(NoticeBoardDto noticeBoardDto) {
		return notieceBoardMapper.noticeInsert(noticeBoardDto);
	}
	
	//select 공지 전체
	public List<BoardIdDto> noticeSelectIdAll(){
		return notieceBoardMapper.noticeSelectIdAll();
	}
	
	//select 공지 삭제안된것만
	public List<BoardIdDto> noticeSelectIdAllNormal(){
		return notieceBoardMapper.noticeSelectIdAllNormal();
	}
	
	//select 공지 중요도 이상만
	public List<BoardIdDto> noticeSelectIdAllNormalImportance(int importance) {
		return notieceBoardMapper.noticeSelectIdAllNormalImportance(importance);
	}
	
	//select 공지 하나만
	public NoticeBoard noticeSelectOne(int boardId) {
		return notieceBoardMapper.noticeSelectOne(boardId);
	}
	
	//select 공지 하나만 id 랑 같이 하나만
	public BoardIdDto noticeSelectIdOne(int boardId) {
		return notieceBoardMapper.noticeSelectIdOne(boardId);
	}
	
	//update 공지 수정
	public int noticeUpdate(NoticeBoardDto noticeBoardDto) {
		return notieceBoardMapper.noticeUpdate(noticeBoardDto);
	}
	
	//delete 삭제
	public int noticeUpdateDelete(int boardId) {
		return notieceBoardMapper.noticeUpdateDelete(boardId);
	}
	
	//delete 실제 DB 에서 삭제
	public int noticeDelete(int boardId) {
		return notieceBoardMapper.noticeDelete(boardId);
	}
}
