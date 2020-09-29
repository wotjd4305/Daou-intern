package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ChatroomRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ChatroomDao implements IChatroomDao {
	
	private String ns = "chatroom.";
	
	private final SqlSession sqlSession;
	

	@Override
	public int getCount(long id) {
		return sqlSession.selectOne(ns + "getCount", id);
	}

	@Override
	public int insertChatroom(ChatroomRequest chatroom) {
		return sqlSession.insert(ns + "insertChatroom", chatroom);
	}

}
