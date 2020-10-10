package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MessageRequest {
	private long chatroomId;
	private int idx;

	@Builder
	public MessageRequest(long chatroomId, int idx) {
		this.chatroomId = chatroomId;
		this.idx = idx;
	}
}
