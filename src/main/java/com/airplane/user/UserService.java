package com.airplane.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	//private UserDao userDao;
	private UserMapper userMapper;
	
	public void regist(UserRegisterRequest cmdObj) {
		System.out.println("userService.regist 동작");
		UserDto userDto = new UserDto();

		userDto.setId(cmdObj.getId());
		userDto.setPassword(cmdObj.getPassword());
		userDto.setName(cmdObj.getName());
		userDto.setGender(cmdObj.getGender());
		userDto.setAge(cmdObj.getAge());
		userDto.setPhoneNumber(cmdObj.getPhoneNumber());

		// 이 코드가 있어야 DB에 등록됨
		userMapper.insertUser(userDto);

		System.out.println("회원가입 완료. 자동 생성된 키: " + userDto.getUserId());
	}
	
	public boolean login(LoginRequestCommand cmd) {
		System.out.println("로그인 서비스 동작");
		//User user = userDao.select(cmd.getUsername(), cmd.getPassword()); //회원 정보 검색
		User user = userMapper.selectUser(cmd.getId(), cmd.getPassword()); //회원 정보 검색
		System.out.println(user);
		return (user != null) ? true : false;
	}
	
	public User search(String id) {
		System.out.println("회원 정보 검색");
		User user = userMapper.idsearch(id); //회원 정보 검색
		return user;
	}
	
	// 아이디로 관리자 여부 체크
    public boolean isAdmin(int userId) {
    	System.out.println("관리자 여부 확인");
        int admin = userMapper.selectAdminByUserId(userId);
        return admin == 1;
    
	}
	// 회원 탈퇴
    public boolean deleteUser(int userId, String inputPassword) {
    	System.out.println("회원 탈퇴 기능 동작");
        // 1. DB에서 현재 비밀번호 조회
        String dbPassword = userMapper.selectPassword(userId);
        
        // 2. 비밀번호 일치 확인
        if (!dbPassword.equals(inputPassword)) {
        	
            return false;
        }
        
        // 3. 논리 삭제 수행
        int result = userMapper.logicalDeleteUser(userId);
        return result > 0;
    }
    
    //마이페이지 기능
    public void updateUserInfo(UserDto userDto) {
    	System.out.println("회원 정보 수정");
        userMapper.updateUserInfo(userDto);
    }

    public User findUserById(int userId) {
        return userMapper.selectUserById(userId);
    }
	
    //회원 중복가입 제한
    public boolean isDuplicatedId(String id) {
    	System.out.println("회원 아이디 중복 확인");
        return userMapper.checkById(id);
    }
    
    //회원정보수정 비밀번호 변경
    public boolean changePassword(int userId, String currentPw, String newPw, String newPwConfirm) {
    	String storePw = userMapper.getPasswordByUserId(userId);
    	
    	if(!storePw.equals(currentPw)) {
    		return false; //현재 비밀번호 불일치
    	}
    	
    	if(!newPw.equals(newPwConfirm)) {
    		return false;
    	}
    	
    	//새 비밀번호 유효성 검사 (길이 8자 이상, 대소문자, 숫자, 특수문자 포함)
        if (!newPw.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
            return false;
        }
    		
    	userMapper.updatePassword(newPw, userId);
    	return true;
    }
    
    
}







