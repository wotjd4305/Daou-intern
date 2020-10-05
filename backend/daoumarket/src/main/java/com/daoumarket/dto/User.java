package com.daoumarket.dto;

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

}
