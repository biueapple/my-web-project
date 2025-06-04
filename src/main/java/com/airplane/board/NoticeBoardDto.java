package com.airplane.board;

import jakarta.validation.constraints.NotBlank;

public class NoticeBoardDto {
	private int boardId;
	private int userId;
	@NotBlank(message = "{NotBlank.noticeBoardDto.boardTitle}")
	private String boardTitle;
	private String board;
	private int importance=2;
	
	public NoticeBoardDto() {}
	public NoticeBoardDto(NoticeBoard noticeBoard) {
		this.boardId=noticeBoard.getBoardId();
		this.userId=noticeBoard.getUserId();
		this.boardTitle=noticeBoard.getBoardTitle();
		this.board=noticeBoard.getBoard();
		this.importance=noticeBoard.getImportance();
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	
	
}
