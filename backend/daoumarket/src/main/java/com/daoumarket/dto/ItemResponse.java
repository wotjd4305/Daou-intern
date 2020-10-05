package com.daoumarket.dto;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemResponse {

	long itemId;
	int userId;
	String title;
	int price;
	String category;
	String content;
	char status;
	boolean delflag;
	LocalDateTime date;
	boolean favorite;
	List<String> picture = new LinkedList<>();
	User user;
	
	@Builder
	public ItemResponse(long itemId, int userId, String title, int price, String category, String content, char status,
			boolean delflag, LocalDateTime date, boolean favorite, List<String> picture, User user) {
		this.itemId = itemId;
		this.userId = userId;
		this.title = title;
		this.price = price;
		this.category = category;
		this.content = content;
		this.status = status;
		this.delflag = delflag;
		this.date = date;
		this.favorite = favorite;
		this.picture = picture;
		this.user = user;
	}
}
