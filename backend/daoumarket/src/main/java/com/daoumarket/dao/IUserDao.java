package com.daoumarket.dao;

import com.daoumarket.dto.UserDto;

public interface IUserDao {
	// sing up
	public int insertUser(UserDto dto);
	public UserDto getNum(long num);
	
	// login
	public UserDto getUserLogin(UserDto dto);
	
	// edit user
	public int updateUser(UserDto dto);
}
