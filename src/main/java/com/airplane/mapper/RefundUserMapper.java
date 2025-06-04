package com.airplane.mapper;

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
	List<String> seatName(int rid);
	List<String> seatNameNormal(int resolved_id);
	void insertDamage(DamageDto damageDto);
	int updateStatus(int id);
	void insertSavepath(DamageDto damageDto);
	
	//id 를 받아서 insurance_id 를 update
	public void updateInsurance(@Param("id") int id, @Param("insurance") int insurance_id);
}
