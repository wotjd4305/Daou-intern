package com.daoumarket.dao;

import com.daoumarket.dto.User;

public interface IUserDao {
	public int insertUser(User user);
	public User getEmpNum(int empNum);
	public User getUserLogin(User user);
	public int updateUser(User user);
	public User getUserByUserId(int userId);
}
