package com.example.mysite.user;

import java.time.LocalDate;
import java.util.List;

public class RefundUserDto {
	private String name;

	private String f_class;
	private String depart;
	private String arrive;
	private String gender;
	private String seat;
	 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getF_class() {
		return f_class;
	}
	public void setF_class(String f_class) {
		this.f_class = f_class;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	

}
