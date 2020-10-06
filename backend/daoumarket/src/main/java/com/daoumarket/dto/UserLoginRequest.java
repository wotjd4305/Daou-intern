package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginRequest {
	private int empNum;
	private String password;

	@Builder
	public UserLoginRequest(int empNum, String password) {
		this.empNum = empNum;
		this.password = password;
	}
	
}
