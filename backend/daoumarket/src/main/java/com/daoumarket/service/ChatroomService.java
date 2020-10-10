package com.daoumarket.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IChatroomDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ChatroomResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatroomService implements IChatroomService {
	
	private final IChatroomDao chatroomDao;
	
	@Override
	public ResponseEntity<BasicResponse> getJoinedRoomListByUserId(int userId) {
		
		BasicResponse response = new BasicResponse();
		
//		List<ChatroomResponse> roomList = chatroomDao.getJoinedRoomListByUserId(userId);
//		
//		for (int i = 0; i < roomList.size(); i++) {
//			array_type array_element = roomList.size()[i];
//			
//		}
//		
//		
		
		return null;
	}

	
}
