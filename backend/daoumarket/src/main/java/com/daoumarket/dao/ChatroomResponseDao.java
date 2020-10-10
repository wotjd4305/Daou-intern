package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ChatroomResponse;
import com.daoumarket.dto.ChatroomResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ChatroomResponseDao implements IChatroomResponseDao {
	
	private String ns = "chatroomResponse.";
	
	private final SqlSession sqlSession;

	@Override
	public ChatroomResponseEntity getChatroomInfo(ChatroomResponse chatroomResponse) {
		return sqlSession.selectOne(ns + "getChatroomInfo", chatroomResponse);
	}
	
}
