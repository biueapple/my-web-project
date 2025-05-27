package com.airplane.board;

public class BoardDto {
	private int userId;
	private String boardTitle;
	private String board;
	
	public BoardDto(Board board) {
		this.userId = board.getUserId();
		this.boardTitle = board.getBoardTitle();
		this.board = board.getBoard();
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
