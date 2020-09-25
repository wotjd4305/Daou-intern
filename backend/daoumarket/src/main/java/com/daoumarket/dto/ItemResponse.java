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
	long name;
	String title;
	int price;
	String category;
	String content;
	String status;
	boolean delflag;
	LocalDateTime date;
	User user;
	
	@Builder
	public ItemResponse(long id, long userId, long name, String title, int price, String category, String content,
			String status, LocalDateTime date, User user) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.status = status;
		this.date = date;
		this.user = user;
	}
}
