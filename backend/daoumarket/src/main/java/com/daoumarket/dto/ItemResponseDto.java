package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemResponseDto {

	long id;
	long userId;
	long name;
	String title;
	int price;
	String category;
	String content;
	String status;
	LocalDateTime date;
	
	@Builder
	public ItemResponseDto(long id, long userId, long name, String title, int price, String category, String content,
			String status, LocalDateTime date) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.status = status;
		this.date = date;
	}
}
