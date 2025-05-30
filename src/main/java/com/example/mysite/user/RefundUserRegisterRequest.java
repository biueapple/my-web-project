package com.example.mysite.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

// 커맨드 객체 정의
public class RefundUserRegisterRequest {
	@NotNull(message = "{NotBlank.name}")
	private int userId;
//	
//	@NotNull(message = "{NotNull.userRegisterRequest.birthDate}")
//	@Past(message = "{Past.userRegisterRequest.birthDate}")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private LocalDate birthDate;
//	
//	@NotBlank(message = "{NotBlank.userRegisterRequest.gender}")
//	private String gender;
//	@NotBlank(message = "{NotBlank.userRegisterRequest.country}")
//	private String country;


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
//	public LocalDate getBirthDate() {
//		return birthDate;
//	}
//	public void setBirthDate(LocalDate birthDate) {
//		this.birthDate = birthDate;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getCountry() {
//		return country;
//	}
//	public void setCountry(String country) {
//		this.country = country;
//	}

	
	
}
