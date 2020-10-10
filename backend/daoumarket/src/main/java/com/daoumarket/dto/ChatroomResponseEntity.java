package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ChatroomResponseEntity {

	String otherUserName;
	String otherUserImage;
	String recentMessage;
	int unreadMessage;
	
	@Builder
	public ChatroomResponseEntity(String otherUserName, String otherUserImage, String recentMessage,
			int unreadMessage) {
		this.otherUserName = otherUserName;
		this.otherUserImage = otherUserImage;
		this.recentMessage = recentMessage;
		this.unreadMessage = unreadMessage;
	}
}
