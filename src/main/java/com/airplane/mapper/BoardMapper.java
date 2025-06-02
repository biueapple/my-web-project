package com.airplane.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.airplane.board.Board;
import com.airplane.board.BoardDto;
import com.airplane.board.BoardIdDto;
@Mapper
public interface BoardMapper {
	int insert(BoardDto boardDto);
	Board selectOne(@Param("boardId") int boardId);
	BoardIdDto selectIdOne(@Param("boardId") int boardId);
	List<Board> selectAll();
	List<BoardIdDto> selectIdAll();
	int update(Board board);
	int delete(@Param("boardId") int boardId);
}
