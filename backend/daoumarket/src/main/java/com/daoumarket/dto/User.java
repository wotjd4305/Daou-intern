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
public class User {
	private int userId;
	private int empNum;
	private String name;
	private String password;
	private String department;
	private String image;
	
	@Builder
	public User(int userId, int empNum, String name, String password, String department, String image) {
		this.userId = userId;
		this.empNum = empNum;
		this.name = name;
		this.password = password;
		this.department = department;
		this.image = image;
	}
	
	
	

}
