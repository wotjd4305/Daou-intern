package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemSearchRequest {
	
	private int userId;
	private String[] category;
	private String keyword;
	private int page;
	private Criteria cri;
	
	@Builder
	public ItemSearchRequest(int userId, String[] category, String keyword, int page, Criteria cri) {
		this.userId = userId;
		this.category = category;
		this.keyword = keyword;
		this.page = page;
		this.cri = cri;
	}
}
	
	
	
