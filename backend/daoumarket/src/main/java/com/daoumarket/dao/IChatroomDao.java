package com.daoumarket.dao;

import com.daoumarket.dto.ChatroomRequest;

public interface IChatroomDao {
	public int getCount(long id);
	public int insertChatroom(ChatroomRequest chatroom);
}
