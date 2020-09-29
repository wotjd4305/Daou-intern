package com.daoumarket.dto;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
	String status;
	boolean delflag;
	LocalDateTime date;
	List<String> picture = new LinkedList<>();
	User user;
	
	@Builder
	public ItemResponse(long id, long userId, String title, int price, String category, String content,
			String status, LocalDateTime date, List<String> picture, User user) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.status = status;
		this.date = date;
		this.picture = picture;
		this.user = user;
	}
}
