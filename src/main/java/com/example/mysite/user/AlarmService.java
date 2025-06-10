package com.example.mysite.user;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
public class AlarmService {

	@Scheduled(fixedRate = 1000)
	public void alarmEveryMinute() {
		System.out.println("알람" + LocalDateTime.now());
	}

}
