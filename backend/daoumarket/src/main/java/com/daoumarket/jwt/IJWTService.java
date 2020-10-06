package com.daoumarket.jwt;

import com.daoumarket.dto.TokenRequest;
import com.daoumarket.dto.User;

public interface IJWTService {
	// Jwt를 생성해주는 메소드
	public String makeJwt(User user) throws Exception;
	
	// jwt가 인증된 토큰인지 검사하는 메소드
	public User checkJwt(TokenRequest accessToken) throws Exception;

}
