package com.airplane.board;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardIdDto {
	//게시글 상세보기 및 메인페이지에서 id 보여주기 위한 클래스
	//게시글 고유 id
	private int boardId;
	//유저 고유 id
	private int userId;
	//유저 id
	private String id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFormattedRegistDate() {
		if (registDate == null)
			return "";
		LocalDate registLocalDate = registDate.toLocalDate();
		LocalDate today = LocalDate.now();

		if (registLocalDate.equals(today)) {
			return registDate.format(DateTimeFormatter.ofPattern("HH:mm"));
		} else {
			return registDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		}
	}

	public String getFormattedRegistDateOne() {
		if (registDate == null)
			return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return registDate.format(formatter);
	}

}
