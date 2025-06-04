package com.airplane.board;

import java.time.LocalDateTime;

public class Board {
	//board 에서 select 하기위한 class
	//게시글 고유 id
	private int boardId;
	//유저 고유 id
	private int userId;
	//제목
	private String boardTitle;
	//내용
	private String board;
	//작성일
	private LocalDateTime registDate;
	//상태(정상,삭제)
	private String state;
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", userId=" + userId + ", userManager=" + ", boardTitle="
				+ boardTitle + ", board=" + board + ", registerDate=" + registDate + "]";
	}
	
}
