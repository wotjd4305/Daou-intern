package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class Message {
	private long messageId;
	private long chatroomId;
	private int senderId;
	private int receiverId;
	private String content;
	private LocalDateTime sendTime;
	private LocalDateTime receiveTime;
	
	@Builder
	public Message(long messageId, long chatroomId, int senderId, int receiverId, String content,
			LocalDateTime sendTime, LocalDateTime receiveTime) {
		this.messageId = messageId;
		this.chatroomId = chatroomId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.sendTime = sendTime;
		this.receiveTime = receiveTime;
	}
}
