package com.daoumarket.dao;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.UserDto;
import com.daoumarket.util.AES256Util;
import com.daoumarket.util.EncodePassword;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDao implements IUserDao{

	private final SqlSession sqlSession;
	private String ns = "user.";

	// sign up
	@Override
	public int insertUser(UserDto dto) {
		return sqlSession.insert(ns + "insertUser", dto);
	}

	@Override
	public UserDto getNum(long num) {
		return sqlSession.selectOne(ns + "getNum", num);
	}

	// login
	@Override
	public UserDto getUserLogin(UserDto dto) {
		return sqlSession.selectOne(ns + "getUserLogin", dto);
	}
	
	
}
