package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.mysite.user.mapper.RefundUserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RefundUserService {
	@Autowired
	//private UserDao userDao;
	private RefundUserMapper userMapper;
	
	
	public List<RefundUser>  findByName(int userId) {
		return userMapper.findByUserId(userId);
	}
	public void regist(RefundUserDto cmdObj) {
		// dao를 이용하여 데이터베이스에 저장
		System.out.println("userService.regist 동작");
		
		System.out.println("데이터 가공 및 DTO생성");
		userMapper.refundInsert(cmdObj);
	}
	
	public boolean deleteUserByUserId(int userId) {
		   int deletedCount = userMapper.deleteUserByUserId(userId);
		    return deletedCount > 0;
	}
}







