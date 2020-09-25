package com.daoumarket.dao;

import com.daoumarket.dto.User;

public interface IUserDao {
	// sing up
	public int insertUser(User dto);
	public User getNum(long num);
	
	// login
	public User getUserLogin(User dto);
	
	// edit user
	public int updateUser(User dto);
}
