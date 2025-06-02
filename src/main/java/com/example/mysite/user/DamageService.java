package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysite.user.mapper.RefundUserMapper;

@Service
public class DamageService {
	@Autowired
	private RefundUserMapper refundUserMapper;

	public void registerRequest(DamageDto damageDto) {
		List<String> savepathList = damageDto.getSavepath();

		refundUserMapper.insertDamage(damageDto);
	}
}
