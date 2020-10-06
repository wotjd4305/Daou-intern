package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserEditRequest {
	private int empNum;
	private String password;
	private String department;

	@Builder
	public UserEditRequest(int empNum, String password, String department) {
		this.empNum = empNum;
		this.password = password;
		this.department = department;
	}
}