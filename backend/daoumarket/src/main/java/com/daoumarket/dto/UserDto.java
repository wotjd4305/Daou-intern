package com.daoumarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	private long id;
	private long num;
	private String name;
	private String password;
	private String department;
	private String image;

}
