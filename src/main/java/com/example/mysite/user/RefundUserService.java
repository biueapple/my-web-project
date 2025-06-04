package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.airplane.mapper.RefundUserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RefundUserService {
	@Autowired
	// private UserDao userDao;
	private RefundUserMapper userMapper;

	public List<RefundUser> findByName(int userId) {
		return userMapper.findByUserId(userId);
	}

	public void regist(RefundUserDto cmdObj) {
		// dao를 이용하여 데이터베이스에 저장
		System.out.println("userService.regist 동작");

		System.out.println("데이터 가공 및 DTO생성");
		userMapper.refundInsert(cmdObj);
	}


	public List<String> seatName(int rid) {
		return userMapper.seatName(rid);
	}
	
	public List<String> seatNameNormal(int resolved_id)
	{
		return userMapper.seatNameNormal(resolved_id);
	}

	public boolean updateStatus(int id) {
		int update = userMapper.updateStatus(id);
		return update > 0;
	}
	

}
