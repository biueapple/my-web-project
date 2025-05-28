package com.example.mysite.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.mysite.user.RefundUser;
import com.example.mysite.user.RefundUserDto;

public interface RefundUserMapper {

	int refundInsert(RefundUserDto user);
	RefundUser RefundselectOne(
			@Param("username")String u, @Param("Country")String c);

	RefundUser findByName(String name);
	int deleteUserByName(String name);
}
