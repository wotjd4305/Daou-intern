package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.Chatroom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ChatroomDao implements IChatroomDao {
	
	private String ns = "chatroom.";
	
	private final SqlSession sqlSession;

	@Override
	public List<Chatroom> getJoinedRoomListByUserId(int userId) {
		return sqlSession.selectList(ns + "getJoinedRoomListByUserId", userId);
	}

	@Override
	public Chatroom checkExistChatroom(Chatroom chatroom) {
		return sqlSession.selectOne(ns + "checkExistChatroom", chatroom);
	}

	@Override
	public long insertChatroom(Chatroom chatroom) {
		return sqlSession.insert(ns + "insertChatroom", chatroom);
	}
}
