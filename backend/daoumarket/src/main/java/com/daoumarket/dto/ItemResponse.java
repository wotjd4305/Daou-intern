package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemResponse {

	long id;
	long userId;
	String title;
	int price;
	String category;
	String content;
	LocalDateTime date;

	@Builder
	public ItemResponse(long id, long userId, String title, int price, String category, String content,
			LocalDateTime date) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.date = date;
	}
}
