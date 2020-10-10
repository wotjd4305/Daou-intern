package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Chatroom;

public interface IChatroomDao {
	public List<Chatroom> getJoinedRoomListByUserId(int userId);
	public Chatroom checkExistChatroom(Chatroom chatroom);
	public long insertChatroom(Chatroom chatroom);
}
