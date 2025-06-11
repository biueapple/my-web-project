package com.example.mysite.user;

import java.util.List;


import com.airplane.mapper.AlarmMapper;

public class AirplaneAlarm extends Alarm
{
	AlarmMapper alarmMapper;
	
	public AirplaneAlarm(AlarmMapper alarmMapper) {
		this.alarmMapper=alarmMapper;
	}
	
	@Override
	public void function() {
		List<String> result= alarmMapper.AlarmJoin();
		for(String s: result) {
			System.out.println(s);
		}
	}

}
