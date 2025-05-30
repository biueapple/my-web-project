package com.airplane.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


// 커맨드 객체 정의
public class UserRegisterRequest {
	@NotBlank(message = "{NotBlank.userRegisterRequest.id}")
	private String id;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.password}")
	private String password;
	private String passwordConfirm;
	
	@NotNull(message = "{NotNull.userRegisterRequest.name}")
	private String name;
	@NotBlank(message = "{NotBlank.userRegisterRequest.gender}")
	private String gender;
	@NotBlank(message = "{NotBlank.userRegisterRequest.age}")
	private String age;
	@NotEmpty(message = "{NotBlank.userRegisterRequest.phoneNumber}")
	private String phoneNumber;
	private int admin;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "UserRegisterRequest [id=" + id + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", name=" + name + ", gender=" + gender + ", age=" + age + ", phoneNumber=" + phoneNumber + ", admin="
				+ admin + "]";
	}
	
	
	

}
