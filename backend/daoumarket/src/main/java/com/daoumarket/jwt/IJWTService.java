package com.daoumarket.jwt;

import com.daoumarket.dto.User;

public interface IJWTService {
	// Jwt�� �������ִ� �޼ҵ�
	public String makeJwt(User res) throws Exception;
	
	// jwt�� ������ ��ū���� �˻��ϴ� �޼ҵ�
	public User checkJwt(String jwt) throws Exception;

}
