package com.daoumarket.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Search {
	private long searchId;
	private String keyword;
	private LocalDateTime date;
	private int userId;
}
