package com.daoumarket.service;

import com.daoumarket.dto.UserDto;

public interface IUserService {
	// sign up
	public int insertUser(UserDto dto);
	public UserDto getNum(long num);
	
	// login
	public UserDto getUserLogin(UserDto dto);
	
	// edit user
	public int updateUser(UserDto dto);
}
