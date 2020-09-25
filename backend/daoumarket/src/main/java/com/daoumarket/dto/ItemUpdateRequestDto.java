package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemUpdateRequestDto {
	
	long id;
	String title;
	int price;
	String category;
	String content;
	String status;
	
	@Builder
	public ItemUpdateRequestDto(long id, String title, int price, String category, String content, String status) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.status = status;
	}
	
	public void updateStatus(String status) {
		this.status = status;
	}
}
