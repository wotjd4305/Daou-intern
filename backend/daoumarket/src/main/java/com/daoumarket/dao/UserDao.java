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

	// sign up
	@Override
	public int insertUser(User dto) {
		return sqlSession.insert(ns + "insertUser", dto);
	}

	@Override
	public User getNum(long num) {
		return sqlSession.selectOne(ns + "getNum", num);
	}

	// login
	@Override
	public User getUserLogin(User dto) {
		return sqlSession.selectOne(ns + "getUserLogin", dto);
	}
	
	// edit user
	@Override
	public int updateUser(User dto) {
		return sqlSession.update(ns + "updateUser", dto);
	}
	
}
