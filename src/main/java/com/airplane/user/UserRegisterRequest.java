package com.airplane.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


// 회원 가입 정보
public class UserRegisterRequest {
	@NotBlank(message = "{NotBlank.userRegisterRequest.id}")
	@Size(min = 4, max = 20, message = "{Size.userRegisterRequest.id}")
	private String id;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.password}")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", 
	message = "{Pattern.userRegisterRequest.password}")
	private String password;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.passwordConfirm}")
	private String passwordConfirm;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.name}")
	@Size(min = 2, message = "{Size.userRegisterRequest.name}")
	private String name;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.gender}")
	private String gender;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.age}")
	@Min(value = 0, message = "{Min.userRegisterRequest.age}")
	private String age;
	
	@NotBlank(message = "{NotBlank.userRegisterRequest.phoneNumber}")
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "{Pattern.userRegisterRequest.phoneNumber}")
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
