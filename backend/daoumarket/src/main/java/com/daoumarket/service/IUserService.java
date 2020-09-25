package com.daoumarket.service;

import com.daoumarket.dto.User;

public interface IUserService {
	// sign up
	public int insertUser(User user);
	public User getNum(long num);
	
	// login
	public User getUserLogin(User user);
	
	// edit user
	public int updateUser(User user);
}
