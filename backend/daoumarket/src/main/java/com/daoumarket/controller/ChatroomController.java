package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
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

	@GetMapping("/room/{userId}")
	@ApiOperation("userId를 가진 사용자의 채팅방 목록 가져오기")
	public ResponseEntity<BasicResponse> getJoinedRoomListByUserId(@PathVariable int userId) {
		log.info("ChatroomController : getJoinedRoomListByUserId => {}", userId);
		
		return chatroomService.getJoinedRoomListByUserId(userId);
	}
    
}