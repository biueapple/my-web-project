package com.example.mysite.user;

public class RefundUserDto {
	private int userId;
	private int reservation_id;
	private String depart;
	private String arrive;
	private String gender;
	private String seat;
	private String id;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getReservation_id()
	{
		return reservation_id;
	}
	public void setReservation_id(int reservation_id)
	{
		this.reservation_id = reservation_id;
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
