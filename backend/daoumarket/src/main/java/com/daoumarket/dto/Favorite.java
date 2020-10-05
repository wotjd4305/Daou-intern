package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Favorite {
	private long favoriteId;
	private long userId;
	private long itemId;
	private LocalDateTime date;
}
