package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IUserDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.User;
import com.daoumarket.dto.UserEditRequest;
import com.daoumarket.dto.UserLoginRequest;
import com.daoumarket.jwt.IJWTService;
import com.daoumarket.util.EncodePassword;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

	private final IUserDao userDao;
	private final IJWTService jwtService;
	
	@Override
	public ResponseEntity<BasicResponse> insertUser(User user) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		User encodePassword = EncodePassword.Encode(user);
		
		int res = userDao.insertUser(encodePassword);

		if (res > 0) {
			response.isSuccess = true;
			response.data = "Success in signup";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
			
		} else {
			response.isSuccess = false;
			response.data = "Fail in signup";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<BasicResponse> getEmpNum(int empNum) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		User userRes = userDao.getEmpNum(empNum);
		
		if (userRes == null) {
			response.isSuccess = false;
			response.data = "No Duplication of Employee Number";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
			
		} else {
			response.isSuccess = true;
			response.data = "Duplication of Employee Number";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<BasicResponse> getUserLogin(UserLoginRequest userLoginRequest) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		User user = User.builder()
				.empNum(userLoginRequest.getEmpNum())
				.password(userLoginRequest.getPassword()).build();
		
		User encodePassword = EncodePassword.Encode(user);
		User userRes = userDao.getUserLogin(encodePassword);
		
		if (userRes == null) {
			response.isSuccess = false;
			response.data = "Discorrect";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = true;
			response.data = "Correct";
			
			try {
				String token = jwtService.makeJwt(userRes);
				
				response.data = token;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);		
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<BasicResponse> updateUser(UserEditRequest userEditRequest) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		User user = User.builder()
				.empNum(userEditRequest.getEmpNum())
				.password(userEditRequest.getPassword())
				.department(userEditRequest.getDepartment()).build();
		
		User encodePassword = EncodePassword.Encode(user);
		
		int res = userDao.updateUser(encodePassword);
		
		if (res > 0) {
			
			try {
				User userRes = userDao.getUserLogin(encodePassword);
				
				String token = jwtService.makeJwt(userRes);
				
				response.data = token;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.isSuccess = true;
			response.data = "Modify";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
			
		} else {
			response.isSuccess = false;
			response.data = "Unable to Modify";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
}
