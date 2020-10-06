package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatroomResponse {
	
	private long chatroomId;
	private long itemId;
	private int sellerId;
	private int buyerId;
	private User otherInfo;
	private String lastMessage;
	private int unreadMessageCnt;
	private LocalDateTime lastMessageDate;
	
	@Builder
	public ChatroomResponse(long chatroomId, long itemId, int sellerId, int buyerId, User otherInfo, String lastMessage,
			int unreadMessageCnt, LocalDateTime lastMessageDate) {
		this.chatroomId = chatroomId;
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.otherInfo = otherInfo;
		this.lastMessage = lastMessage;
		this.unreadMessageCnt = unreadMessageCnt;
		this.lastMessageDate = lastMessageDate;
	}
}