package com.example.mysite.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.RefundUserMapper;
import com.airplane.user.LoginRequestCommand;
import com.airplane.user.User;
import com.airplane.user.UserService;

@Service
public class UploadService {
	@Autowired
	UserService userService;
	@Autowired
	DamageService damageService;
	@Autowired
	RefundUserMapper refundUserMapper;
	
	public void service(LoginRequestCommand lrc, String path) {
		String id = lrc.getId();
		User user = userService.search(id);
		
		DamageDto damageDto = new DamageDto();
		damageDto.setId(user.getUserId());
		damageDto.setSavepath(path);
		
		refundUserMapper.insertSavepath(damageDto);

	}
}
