package com.airplane.board;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class Board {
	private int boardId;
	private int userId;
	@NotBlank(message = "{NotBlank.board.boardTitle}")
	private String boardTitle;
	private String board;
	private LocalDateTime registDate;
	
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
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", userId=" + userId + ", userManager=" + ", boardTitle="
				+ boardTitle + ", board=" + board + ", registerDate=" + registDate + "]";
	}
	
}
