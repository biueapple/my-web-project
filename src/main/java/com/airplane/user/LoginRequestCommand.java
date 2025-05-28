package com.airplane.user;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestCommand {			//로그인 유효성 검증
	@NotBlank(message = "{NotBlank.id}")
	private String id;
	
	@NotBlank(message = "{NotBlank.password}")
	private String password;
	
	private boolean remember;
	
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
