package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ItemSearchRequest {
	
	private int userId;
	private String[] category;
	private String keyword;
	
	@Builder
	public ItemSearchRequest(int userId, String[] category, String keyword) {
		this.userId = userId;
		this.category = category;
		this.keyword = keyword;
	}
}
