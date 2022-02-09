package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.UserRepository;
import com.poscoict.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired // DI를 하기위해서 USERREPOSITORY로무터 정보가 USERSERVICE로 들어오게 하기위해서
	private UserRepository userRepository;

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}

	public UserVo getUser(String email, String password) { //email과 password를 받으면
		return userRepository.findByEmailAndPassword(email, password);  // UserVo 타입으로 no, name from user 이값을 반환한다.
	}

	public UserVo getUser(Long userNo) {
		return userRepository.findByNo(userNo);  // UserVo 타입으로 no, name, email, gender
	}

	public void updateUser(UserVo userVo) {
		userRepository.update(userVo);
	}
	
}