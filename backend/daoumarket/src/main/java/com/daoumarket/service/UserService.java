package com.daoumarket.service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IUserDao;
import com.daoumarket.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

	private final IUserDao userDao;
	
	// sign up
	@Override
	public int insertUser(User dto) {
		return userDao.insertUser(dto);
	}
	
	@Override
	public User getNum(long num) {
		return userDao.getNum(num);
	}
	
	// login
	@Override
	public User getUserLogin(User dto) {
		return userDao.getUserLogin(dto);
	}
	
	// edit user
	@Override
	public int updateUser(User dto) {
		return userDao.updateUser(dto);
	}
}
