package com.airplane.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.user.mapper.UserMapper;

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

		System.out.println("회원가입 완료. 자동 생성된 키: " + userDto.getId());
	}
	public boolean login(LoginRequestCommand cmd) {
		System.out.println("로그인 서비스 동작");
		//User user = userDao.select(cmd.getUsername(), cmd.getPassword()); //회원 정보 검색
		User user = userMapper.selectUser(cmd.getId(), cmd.getPassword()); //회원 정보 검색
		System.out.println(user);
		return (user != null) ? true : false;
	}
	

	public User search(String id) {
		User user = userMapper.idsearch(id); //회원 정보 검색
		return user;
	}
	
	// 아이디로 관리자 여부 체크
    public boolean isAdmin(String userId) {
        int admin = userMapper.selectAdminByUserId(userId);
        return admin == 1;
    
	}
	
	
}







