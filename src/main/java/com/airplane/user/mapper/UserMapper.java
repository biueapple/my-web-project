package com.airplane.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.airplane.user.User;
import com.airplane.user.UserDto;

public interface UserMapper {
	
	// 회원가입
	int insertUser(UserDto userDto);
	// 로그인 시 사용 (id + password로 검색)
	User selectUser(
			@Param("id")String id, 
			@Param("password")String password);
	
	// 아이디 확인
	User idsearch(@Param("id")String id);
	
	// 해당 userId가 관리자 권한인지 조회
	int selectAdminByUserId(int userId);
	
	// 회원 탈퇴전 비밀번호 확인
	String selectPassword(int userId);
	
	//회원 탈퇴
	int logicalDeleteUser(int userId);
	
}
