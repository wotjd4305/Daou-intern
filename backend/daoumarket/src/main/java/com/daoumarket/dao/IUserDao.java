package com.daoumarket.dao;

import com.daoumarket.dto.User;
import com.daoumarket.dto.UserLoginRequest;

public interface IUserDao {
	public int insertUser(User user);
	public User getEmpNum(long empNum);
	public User getUserLogin(UserLoginRequest userLogin);
	public int updateUser(User user);
}
