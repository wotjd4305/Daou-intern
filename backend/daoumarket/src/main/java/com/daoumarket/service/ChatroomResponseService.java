package com.daoumarket.service;

import org.springframework.stereotype.Service;

import com.daoumarket.dao.IChatroomResponseDao;
import com.daoumarket.dto.ChatroomResponse;
import com.daoumarket.dto.ChatroomResponseEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatroomResponseService implements IChatRoomResponseService {

	private final IChatroomResponseDao chatroomResponseDao;

	@Override
	public ChatroomResponseEntity getChatRoomInfo(ChatroomResponse chatroomResponse) {
		return chatroomResponseDao.getChatroomInfo(chatroomResponse);
	}
}
