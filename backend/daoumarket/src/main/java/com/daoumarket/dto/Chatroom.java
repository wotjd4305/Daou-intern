package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class Chatroom {
	
	long chatroomId;
	long itemId;
	int buyerId;
	int sellerId;
	
	@Builder
	public Chatroom(long chatroomId, long itemId, int buyerId, int sellerId) {
		this.chatroomId = chatroomId;
		this.itemId = itemId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
	}
}
