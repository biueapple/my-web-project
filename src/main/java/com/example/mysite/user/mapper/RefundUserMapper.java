package com.example.mysite.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.mysite.user.DamageDto;
import com.example.mysite.user.RefundUser;
import com.example.mysite.user.RefundUserDto;

public interface RefundUserMapper {

	int refundInsert(RefundUserDto user);
	RefundUser RefundselectOne(
			@Param("userId")String u);

	List<RefundUser> findByUserId(int userId);
	//int deleteUserByUserId(int userId);
	List<String> seatName(int rid);
	void insertDamage(DamageDto damageDto);
	int updateStatus(int id);
}
