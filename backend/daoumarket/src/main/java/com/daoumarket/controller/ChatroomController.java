package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Chatroom;
import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.Message;
import com.daoumarket.service.IChatroomService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ChatroomController {
	
	private final IChatroomService chatroomService;
	private final int PAGE = 10;

	@GetMapping("/chatroom/{userId}")
	@ApiOperation("userId를 가진 사용자의 채팅방 목록 가져오기")
	public ResponseEntity<BasicResponse> getJoinedRoomListByUserId(@PathVariable int userId) {
		log.info("ChatroomController : getJoinedRoomListByUserId => {}", userId);
		
		return chatroomService.getJoinedRoomListByUserId(userId);
	}
	
	@PostMapping("/chatroom")
	@ApiOperation("채팅방 생성")
	public ResponseEntity<BasicResponse> insertRoom(@RequestBody Chatroom chatroom) {
		log.info("ChatroomController : insertRoom");
		
		return chatroomService.insertRoom(chatroom);
	}

	@GetMapping("/chatroom/{chatroomId}/message")
	@ApiOperation("채팅방의 메세지 " + PAGE + "개 보기")
	public ResponseEntity<BasicResponse> getMessageByChatroomId(@PathVariable long chatroomId, @RequestParam(defaultValue = "0") int page) {
		log.info("ChatroomController : getMessageByChatroomId => {}", chatroomId);
		
		return chatroomService.getMessageByChatroomId(chatroomId, page);
	}

	@PatchMapping("/chatroom/message")
	@ApiOperation("채팅방 읽음 처리")
	public ResponseEntity<BasicResponse> updateReadtime(@RequestBody ChatroomRequest chatroom) {
		log.info("ChatroomController : updateReadtime => {}", chatroom.getChatroomId());
		
		return chatroomService.updateReadTime(chatroom);
	}		
	
	@MessageMapping("/message")
	public ResponseEntity<BasicResponse> sendMessage(@Payload Message message) {
		log.info("ChatroomController : sendMessage " +  "전달 메세지 : " + message);
		
		return chatroomService.sendMessage(message);
	}
}