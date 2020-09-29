package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatroomResponse {
	
	private long id;
	private long itemId;
	private long sellerId;
	private long buyerId;
	private String lastMessage;
	private int unreadMessageCnt;
	private User user;
	
	@Builder
	public ChatroomResponse(long id, long itemId, long sellerId, long buyerId, String lastMessage, int unreadMessageCnt,
			User user) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.lastMessage = lastMessage;
		this.unreadMessageCnt = unreadMessageCnt;
		this.user = user;
	}
	
}