package com.example.mysite.user;

import java.time.LocalDate;
import java.util.List;

public class RefundUserDto {
	private String name;

	private LocalDate birthDate;
	private String gender;
	private String country;
	 
	public String getUsername() {
		return name;
	}
	public void setUsername(String name) {
		this.name = name;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
