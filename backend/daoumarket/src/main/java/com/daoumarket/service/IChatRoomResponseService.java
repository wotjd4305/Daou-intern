package com.daoumarket.service;

import com.daoumarket.dto.ChatroomResponse;
import com.daoumarket.dto.ChatroomResponseEntity;

public interface IChatRoomResponseService {
	public ChatroomResponseEntity getChatRoomInfo(ChatroomResponse chatroomResponse);
}
