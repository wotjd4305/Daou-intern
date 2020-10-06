package com.daoumarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchInsertResponse {
	private int userId;
	private String keyword;
	private int result;
}
