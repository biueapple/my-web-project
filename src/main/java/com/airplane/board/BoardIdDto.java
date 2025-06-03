package com.airplane.board;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardIdDto {
	private int boardId;
	private int userId;
	private String id;
	private String boardTitle;
	private String board;
	private LocalDateTime registDate;
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
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(registDate, now);

		if (duration.toHours() < 24) {
			// 24시간 이내면 시간만 (예: 15:30)
			return registDate.format(DateTimeFormatter.ofPattern("HH:mm"));
		} else {
			// 24시간 이상이면 날짜만 (예: 2025-06-02)
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
