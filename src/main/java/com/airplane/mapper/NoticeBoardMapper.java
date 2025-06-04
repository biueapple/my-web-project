package com.airplane.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airplane.board.Board;
import com.airplane.board.BoardDto;
import com.airplane.board.BoardIdDto;
import com.airplane.board.NoticeBoard;
import com.airplane.board.NoticeBoardDto;

public interface NoticeBoardMapper {
	//noticeBoard
	int noticeInsert(NoticeBoardDto noticeBoardDto);
	NoticeBoard noticeSelectOne(@Param("boardId") int boardId);
	BoardIdDto noticeSelectIdOne(@Param("boardId") int boardId);
	BoardIdDto noticeSelectIdOneNormal(@Param("boardId") int boardId);
	List<BoardIdDto> noticeSelectIdAll();
	List<BoardIdDto> noticeSelectIdAllNormal();
	List<BoardIdDto> noticeSelectIdAllNormalImportance(@Param("importance") int importance);
	int noticeUpdate(NoticeBoardDto noticeBoardDto);
	int noticeDelete(@Param("boardId") int boardId);
	int noticeUpdateDelete(@Param("boardId") int boardId);
}
