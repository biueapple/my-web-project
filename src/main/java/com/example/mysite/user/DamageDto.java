package com.example.mysite.user;

import java.util.List;

public class DamageDto {
	private String id;
	private List<String> savepath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getSavepath() {
		return savepath;
	}

	public void setSavepath(List<String> savepath) {
		this.savepath = savepath;
	}

}
