package com.airplane.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	//board
	public int insertBoard(BoardDto boardDto) {
		return boardMapper.insert(boardDto);
	}
	
	public List<Board> selectAll(){
		return boardMapper.selectAll();
	}
	
	public Board selectOne(int boardId) {
		return boardMapper.selectOne(boardId);
	}
	
	public int update(Board board) {
		return boardMapper.update(board);
	}
	
	public int updateDelete(int boardId) {
		return boardMapper.updateDelete(boardId);
	}
	
	public int delete(int boardId) {
		return boardMapper.delete(boardId);
	}
	
	public List<BoardIdDto> selectIdAll(){
		return boardMapper.selectIdAll();
	}
	
	public List<BoardIdDto> selectIdAllNormal(){
		return boardMapper.selectIdAllNormal();
	}
	
	public BoardIdDto selectIdOne(int boardId) {
		return boardMapper.selectIdOne(boardId);
	}
	
	public BoardIdDto selectIdOneNormal(int boardId) {
		return boardMapper.selectIdOneNormal(boardId);
	}
	
	//noticeBoard
	public int insertNoticeBoard(BoardDto boardDto) {
		return boardMapper.noticeInsert(boardDto);
	}
	
	public List<BoardIdDto> noticeSelectIdAll(){
		return boardMapper.noticeSelectIdAll();
	}
	
	public List<BoardIdDto> noticeSelectIdAllNormal(){
		return boardMapper.noticeSelectIdAllNormal();
	}
	
	public BoardIdDto noticeSelectIdOne(int boardId) {
		return boardMapper.noticeSelectIdOne(boardId);
	}
	
	public BoardIdDto noticeSelectIdOneNormal(int boardId) {
		return boardMapper.noticeSelectIdOneNormal(boardId);
	}
	
	public Board noticeSelectOne(int boardId) {
		return boardMapper.noticeSelectOne(boardId);
	}
	
	public int noticeUpdate(Board board) {
		return boardMapper.noticeUpdate(board);
	}
	
	public int noticeUpdateDelete(int boardId) {
		return boardMapper.noticeUpdateDelete(boardId);
	}
	
	public int noticeDelete(int boardId) {
		return boardMapper.noticeDelete(boardId);
	}
}
