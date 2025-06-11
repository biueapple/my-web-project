package com.airplane.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airplane.board.Board;
import com.airplane.board.BoardDto;
import com.airplane.board.BoardIdDto;

public interface BoardMapper {
	//board
	int insert(BoardDto boardDto);
	Board selectOne(@Param("boardId") int boardId);
	BoardIdDto selectIdOne(@Param("boardId") int boardId);
	List<Board> selectAll();
	List<BoardIdDto> selectIdAll();
	List<BoardIdDto> selectIdAllNormal();
	List<BoardIdDto> selectIdAllNormalId(@Param("userId")int userId);
	int update(BoardDto boardDto);
	int delete(@Param("boardId") int boardId);
	int updateDelete(@Param("boardId") int boardId);
}
