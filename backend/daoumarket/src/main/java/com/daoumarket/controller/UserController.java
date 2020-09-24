package com.daoumarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.UserDto;
import com.daoumarket.service.IUserService;
import com.daoumarket.util.EncodePassword;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {
	
	private final IUserService userService;
	
	// sign up
	@PostMapping("/api/user")
	@ApiOperation("회원가입")
	public ResponseEntity<BasicResponse> insertUser(@RequestBody UserDto dto) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		new EncodePassword(dto);
		
		int res = userService.insertUser(dto);

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
	
	
	@GetMapping("/api/{num}")
	@ApiOperation("아이디 중복 체크")
	public ResponseEntity<BasicResponse> getNum(@PathVariable long num) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		UserDto res = userService.getNum(num);
		
		if (res == null) {
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
	
	// login
	@PostMapping("/api/login")
	public ResponseEntity<BasicResponse> getUserLogin(@RequestBody UserDto dto){
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		new EncodePassword(dto);
		
		UserDto res = userService.getUserLogin(dto);
		
		if (res == null) {
			basicResponse.status = false;
			basicResponse.data = "Discorrect";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = true;
			basicResponse.data = "Correct";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			
		}
		
		return responseEntity;
	}
	
	
	
	

	
	
}
