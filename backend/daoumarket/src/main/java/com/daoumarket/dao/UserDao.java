package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.User;
import com.daoumarket.dto.UserLoginRequest;

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
	public User getEmpNum(long empNum) {
		return sqlSession.selectOne(ns + "getEmpNum", empNum);
	}

	@Override
	public User getUserLogin(UserLoginRequest userLogin) {
		return sqlSession.selectOne(ns + "getUserLogin", userLogin);
	}
	
	@Override
	public int updateUser(User user) {
		return sqlSession.update(ns + "updateUser", user);
	}
	
}
