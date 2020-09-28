package com.daoumarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequest {
	private long num;
	private String password;
}
