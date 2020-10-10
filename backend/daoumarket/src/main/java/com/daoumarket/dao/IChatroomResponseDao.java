package com.daoumarket.dao;

import com.daoumarket.dto.ChatroomResponse;
import com.daoumarket.dto.ChatroomResponseEntity;

public interface IChatroomResponseDao {
	public ChatroomResponseEntity getChatroomInfo(ChatroomResponse chatroomRes);
}
