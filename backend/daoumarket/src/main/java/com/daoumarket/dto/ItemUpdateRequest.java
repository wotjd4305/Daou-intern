package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemUpdateRequest {
	
	private long itemId;
	private String title;
	private int price;
	private String category;
	private String content;
	private char status;
	
	@Builder
	public ItemUpdateRequest(long itemId, String title, int price, String category, String content, char status) {
		this.itemId = itemId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.status = status;
	}
	
	public void updateStatus(char status) {
		this.status = status;
	}
}
