package com.example.mysite.user;

public class Join {
	
		private int id;
		private int userId;
		private String depart;
		private String arrive;
		private String seat;
		private String gender;
		private String state;
		private String userName;
		private String insuranceName;
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
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
		public String getSeat() {
			return seat;
		}
		public void setSeat(String seat) {
			this.seat = seat;
		}
		
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getInsuranceName() {
			return insuranceName;
		}
		public void setInsuranceName(String insuranceName) {
			this.insuranceName = insuranceName;
		}

	

}
