package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.ChatroomResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ChatroomDao implements IChatroomDao {
	
	private String ns = "chatroom.";
	
	private final SqlSession sqlSession;

	@Override
	public List<ChatroomResponse> getJoinedRoomListByUserId(int userId) {
		return sqlSession.selectList(ns + "getJoinedRoomListByUserId", userId);
	}
	
}
