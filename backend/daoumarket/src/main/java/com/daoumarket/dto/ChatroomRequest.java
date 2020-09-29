package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatroomRequest {
	
	private long id;
	private long item_id;
	private long seller_id;
	private long buyer_id;
	
	@Builder
	public ChatroomRequest(long id, long item_id, long seller_id, long buyer_id) {
		super();
		this.id = id;
		this.item_id = item_id;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
	}
}