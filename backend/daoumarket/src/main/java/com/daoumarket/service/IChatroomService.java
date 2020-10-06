package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;

public interface IChatroomService {
	public ResponseEntity<BasicResponse> getJoinedRoomListByUserId(int userId);
}
