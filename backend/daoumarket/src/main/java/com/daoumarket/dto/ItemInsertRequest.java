package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemInsertRequest {
	
	private long itemId;
	private int userId;
	private String title;
	private int price;
	private String category;
	private String content;
	
	@Builder
	public ItemInsertRequest(long itemId, int userId, String title, int price, String category, String content) {
		this.itemId = itemId;
		this.userId = userId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
	}
	
	
}
