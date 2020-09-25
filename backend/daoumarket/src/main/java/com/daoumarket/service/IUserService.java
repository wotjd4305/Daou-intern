package com.daoumarket.service;

import com.daoumarket.dto.User;

public interface IUserService {
	// sign up
	public int insertUser(User dto);
	public User getNum(long num);
	
	// login
	public User getUserLogin(User dto);
	
	// edit user
	public int updateUser(User dto);
}
