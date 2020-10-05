package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDao implements IUserDao{

	private final SqlSession sqlSession;
	private String ns = "user.";

	@Override
	public int insertUser(User user) {
		return sqlSession.insert(ns + "insertUser", user);
	}

	@Override
	public User getEmpNum(int empNum) {
		return sqlSession.selectOne(ns + "getEmpNum", empNum);
	}

	@Override
	public User getUserLogin(User user) {
		return sqlSession.selectOne(ns + "getUserLogin", user);
	}
	
	@Override
	public int updateUser(User user) {
		return sqlSession.update(ns + "updateUser", user);
	}

	@Override
	public User getUserByUserId(int userId) {
		return sqlSession.selectOne(ns + "getUserByUserId", userId);
	}
	
}
