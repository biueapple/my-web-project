package com.airplane.user;

public class User {
	private int Userid;
    private String id;
    private String password;
    private String name;
    private String gender;
    private String age;
    private String phoneNumber;
    
    
    public User(int Userid, String id, String password, String name, String gender, String age,
			String phoneNumber) {
		super();
		this.Userid = Userid;
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}
	
	
	public int getUserId() {
		return Userid;
	}
	public void setUserId(int Userid) {
		this.Userid = Userid;
	}
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

    
}