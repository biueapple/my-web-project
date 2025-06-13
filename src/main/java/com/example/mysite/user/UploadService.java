package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.RefundUserMapper;
import com.airplane.user.UserService;

@Service
public class UploadService {
	@Autowired
	UserService userService;
	@Autowired
	DamageService damageService;
	@Autowired
	RefundUserMapper refundUserMapper;
	
	public void service(List<Integer> ids, String path) {
		for(Integer id : ids) {
		DamageDto damageDto = new DamageDto();
		damageDto.setId(id);
		damageDto.setSavepath(path);
		
		refundUserMapper.insertSavepath(damageDto);
		
		}
	}
}
