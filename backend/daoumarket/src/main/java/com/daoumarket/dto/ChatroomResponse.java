package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatroomResponse {
	long chatroomId;
	long currUserId;
	long otherUserId;
	ChatroomResponseEntity entity;

	public void setEntity(ChatroomResponseEntity entity) {
		this.entity = entity;
	}

	@Builder
	public ChatroomResponse(long chatroomId, long currUserId, long otherUserId, ChatroomResponseEntity entity) {
		this.chatroomId = chatroomId;
		this.currUserId = currUserId;
		this.otherUserId = otherUserId;
		this.entity = entity;
	}
}
