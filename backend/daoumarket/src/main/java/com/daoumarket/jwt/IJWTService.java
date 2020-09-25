package com.daoumarket.jwt;

import com.daoumarket.dto.UserDto;

public interface IJWTService {
	// Jwt를 생성해주는 메소드
	public String makeJwt(UserDto res) throws Exception;
	
	// jwt가 인증된 토큰인지 검사하는 메소드
	public UserDto checkJwt(String jwt) throws Exception;

}
