package com.example.mysite.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.airplane.mapper.AlarmMapper;

@Service
@Component
@EnableScheduling
public class AlarmService {
	@Autowired
	public AlarmMapper alarmMapper;

	public Map<String, Alarm> myMap = new HashMap<>();
	public Map<String, Runnable> myMap2 = new HashMap<>();
	
	@Scheduled(fixedRate = 3000)
	public void alarmEveryMinute() {
		for(Alarm a : myMap.values()) {
			a.function();
		}
	}
	
	@Scheduled(fixedRate = 1000*60*5)
	public void alarmFiveMinute() {
		for(Runnable a : myMap2.values()) {
			a.run();
		}
	}
	
}
