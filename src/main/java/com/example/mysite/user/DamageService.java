package com.example.mysite.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.RefundUserMapper;

@Service
public class DamageService {
	@Autowired
	private RefundUserMapper refundUserMapper;

	public void registerRequest(DamageDto damageDto) {
		refundUserMapper.insertDamage(damageDto);
	}

}
