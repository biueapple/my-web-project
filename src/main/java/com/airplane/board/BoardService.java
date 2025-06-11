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
	
	//insert
	public int insertBoard(BoardDto boardDto) {
		return boardMapper.insert(boardDto);
	}
	
	//select 전체
	public List<Board> selectAll(){
		return boardMapper.selectAll();
	}
	
	//select 게시글 id 값으로 하나만
	public Board selectOne(int boardId) {
		return boardMapper.selectOne(boardId);
	}
	
	//update 수정
	public int update(BoardDto boardDto) {
		return boardMapper.update(boardDto);
	}
	
	//delete 삭제
	public int updateDelete(int boardId) {
		return boardMapper.updateDelete(boardId);
	}
	
	//delete db 에서 삭제
	public int delete(int boardId) {
		return boardMapper.delete(boardId);
	}
	
	//select 유저의 id와 같이
	public List<BoardIdDto> selectIdAll(){
		return boardMapper.selectIdAll();
	}
	
	//select 유저의 id와 같이 삭제 안된 것만
	public List<BoardIdDto> selectIdAllNormal(){
		return boardMapper.selectIdAllNormal();
	}
	
	//select 유저의 int id 값으로 삭제 안된 것 가져오기
	public List<BoardIdDto> selectIdAllNormalId(int userId){
		return boardMapper.selectIdAllNormalId(userId);
	}
	
	//select 검색하기
	public List<BoardIdDto> searchBoard(String keyword, String searchType){
		return boardMapper.searchBoard(keyword,searchType);
	}
	
	//select 유저의 id와 같이 하나만
	public BoardIdDto selectIdOne(int boardId) {
		return boardMapper.selectIdOne(boardId);
	}
}
