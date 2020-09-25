package com.daoumarket.service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IUserDao;
import com.daoumarket.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

	private final IUserDao userDao;
	
	// sign up
	@Override
	public int insertUser(UserDto dto) {
		return userDao.insertUser(dto);
	}
	
	@Override
	public UserDto getNum(long num) {
		return userDao.getNum(num);
	}
	
	// login
	@Override
	public UserDto getUserLogin(UserDto dto) {
		return userDao.getUserLogin(dto);
	}
	
	// edit user
	@Override
	public int updateUser(UserDto dto) {
		return userDao.updateUser(dto);
	}
}
