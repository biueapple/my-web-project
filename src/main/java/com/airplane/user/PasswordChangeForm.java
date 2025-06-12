package com.airplane.user;

import jakarta.validation.constraints.NotBlank;

public class PasswordChangeForm {
	
	//현재 비밀번호
	@NotBlank
	private String currentPassword;
	
	//새 비밀번호
	@NotBlank 
	private String newPassword;
	
	//새 비밀번호 확인
	@NotBlank
	private String newPasswordConfirm;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
	
}
