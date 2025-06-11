package com.example.mysite.user;

import java.time.LocalDateTime;

public class PresentTime extends Alarm
{

	@Override
	public void function() {
		// TODO Auto-generated method stub

		System.out.println("알람" + LocalDateTime.now());
	}

}
