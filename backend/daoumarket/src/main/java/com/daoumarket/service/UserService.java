package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IUserDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.User;
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
		BasicResponse basicResponse = new BasicResponse();
		
		User encodePassword = EncodePassword.Encode(user);
		
		int res = userDao.insertUser(encodePassword);

		if (res > 0) {
			basicResponse.status = true;
			basicResponse.data = "Success in signup";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			
		} else {
			basicResponse.status = false;
			basicResponse.data = "Fail in signup";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<BasicResponse> getEmpNum(long empNum) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		User userRes = userDao.getEmpNum(empNum);
		
		if (userRes == null) {
			basicResponse.status = false;
			basicResponse.data = "No Duplication of Employee Number";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			
		} else {
			basicResponse.status = true;
			basicResponse.data = "Duplication of Employee Number";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<BasicResponse> getUserLogin(UserLoginRequest userLogin) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		UserLoginRequest encodePassword = EncodePassword.EncodeLogin(userLogin);
		User userRes = userDao.getUserLogin(encodePassword);
		
		if (userRes == null) {
			basicResponse.status = false;
			basicResponse.data = "Discorrect";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = true;
			basicResponse.data = "Correct";
			
			try {
				String token = jwtService.makeJwt(userRes);
				
				basicResponse.object = token;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);		
		}
		
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<BasicResponse> updateUser(User user) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		User encodePassword = EncodePassword.Encode(user);
		int res = userDao.updateUser(encodePassword);
		
		if (res > 0) {
			
			try {
				String token = jwtService.makeJwt(encodePassword);
				
				basicResponse.object = token;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			basicResponse.status = true;
			basicResponse.data = "Modify";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			
		} else {
			basicResponse.status = false;
			basicResponse.data = "Unable to Modify";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}
}
