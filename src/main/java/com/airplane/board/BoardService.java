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
	
	public int update(BoardDto boardDto) {
		return boardMapper.update(boardDto);
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
}
