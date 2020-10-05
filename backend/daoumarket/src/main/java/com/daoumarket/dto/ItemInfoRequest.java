package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemInfoRequest {
	
	private long itemId;
	private int userId;
	
	@Builder
	public ItemInfoRequest(long itemId, int userId) {
		this.itemId = itemId;
		this.userId = userId;
	}
}
