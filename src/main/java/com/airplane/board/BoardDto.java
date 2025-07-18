package com.airplane.board;

import jakarta.validation.constraints.NotBlank;

public class BoardDto {
	//board 에 insert 또는 update 하기위한 클래스
	private int boardId;
	private int userId;
	//제목은 무조건 입력받기
	@NotBlank(message = "{NotBlank.boardDto.boardTitle}")
	private String boardTitle;
	private String board;
	
	public BoardDto() {}
	public BoardDto(Board board) {
		this.boardId=board.getBoardId();
		this.userId=board.getUserId();
		this.boardTitle=board.getBoardTitle();
		this.board=board.getBoard();
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
	
	
}
