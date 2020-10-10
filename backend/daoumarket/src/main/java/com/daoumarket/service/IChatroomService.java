package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Chatroom;
import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.Message;

public interface IChatroomService {
	public ResponseEntity<BasicResponse> getJoinedRoomListByUserId(int userId);
	public ResponseEntity<BasicResponse> insertRoom(Chatroom chatroom);
	public ResponseEntity<BasicResponse> getMessageByChatroomId(long chatroomId, int page);
	public ResponseEntity<BasicResponse> updateReadTime(ChatroomRequest chatroom);
	public ResponseEntity<BasicResponse> sendMessage(Message message);
}
