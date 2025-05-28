package com.airplane.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.airplane.user.User;
import com.airplane.user.UserDto;

public interface UserMapper {
	
	int insertUser(UserDto userDto);
	User selectUser(
			@Param("id")String id, 
			@Param("password")String password);
	
}
