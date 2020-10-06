package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.User;
import com.daoumarket.dto.UserEditRequest;
import com.daoumarket.dto.UserLoginRequest;

public interface IUserService {
	public ResponseEntity<BasicResponse> insertUser(User user);
	public ResponseEntity<BasicResponse> getEmpNum(int empNum);
	public ResponseEntity<BasicResponse> getUserLogin(UserLoginRequest userLogin);
	public ResponseEntity<BasicResponse> updateUser(UserEditRequest userEdit);
}
