package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemSearchRequest {
	
	String[] category;
	String keyword;
	
	@Builder
	public ItemSearchRequest(String[] category, String keyword) {
		this.category = category;
		this.keyword = keyword;
	}
}
