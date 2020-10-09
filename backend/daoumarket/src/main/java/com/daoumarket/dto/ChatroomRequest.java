package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatroomRequest {
	
	private long itemId;
	private int sellerId;
	private int buyerId;
	
	@Builder
	public ChatroomRequest(long itemId, int sellerId, int buyerId) {
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
	}
}