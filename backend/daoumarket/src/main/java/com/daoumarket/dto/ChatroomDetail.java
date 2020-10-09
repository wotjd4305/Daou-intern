package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatroomDetail {
	
	private String otherUserName;
	private String otherUserImage;
	private String lastMessage;
	private int unreadMessageCnt;
	private LocalDateTime lastMessageDate;
	
	@Builder
	public ChatroomDetail(String otherUserName, String otherUserImage, String lastMessage, int unreadMessageCnt,
			LocalDateTime lastMessageDate) {
		this.otherUserName = otherUserName;
		this.otherUserImage = otherUserImage;
		this.lastMessage = lastMessage;
		this.unreadMessageCnt = unreadMessageCnt;
		this.lastMessageDate = lastMessageDate;
	}
}