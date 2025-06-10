package com.example.mysite.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.RefundUserMapper;

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
	public List<Join> selectAll() {
		return userMapper.selectAll();
	}
	
	public boolean updateStatus(int id) {
		int update = userMapper.updateStatus(id);
		return update > 0;
	}
	
	public void updateInsurance(int seat_id, int insurance_id)
	{
		userMapper.updateInsurance(seat_id, insurance_id);
	}
}
