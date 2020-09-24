package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemSaveRequest {
	
	long id;
	long userId;
	String title;
	int price;
	String category;
	String content;
	
	@Builder
	public ItemSaveRequest(long userId, String title, int price, String category, String content) {
		this.userId = userId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
	}
}
