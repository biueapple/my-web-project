package com.airplane.user;

import jakarta.validation.constraints.NotBlank;

public class UserDeleteCommand {
	
	// 회원 탈퇴 본인 확인용 비밀번호입력 요청
	  @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
	  
	//getter, setter
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}