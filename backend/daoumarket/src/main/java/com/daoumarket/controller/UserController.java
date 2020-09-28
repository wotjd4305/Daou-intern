package com.daoumarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.User;
import com.daoumarket.dto.UserLoginRequest;
import com.daoumarket.jwt.IJWTService;
import com.daoumarket.service.IUserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {
	
	private final IUserService userService;
	private final IJWTService jwtService;
	
	@PostMapping("/user")
	@ApiOperation("회원가입")
	public ResponseEntity<BasicResponse> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	
	@GetMapping("/{num}")
	@ApiOperation("사번 중복 체크")
	public ResponseEntity<BasicResponse> getNum(@PathVariable long num) {
		return userService.getNum(num);
	}
	
	@PostMapping("/login")
	@ApiOperation("로그인")
	public ResponseEntity<BasicResponse> getUserLogin(@RequestBody UserLoginRequest userLogin){
		return userService.getUserLogin(userLogin);
	}
	
	@PostMapping("/token")
	@ApiOperation("토큰 검증")
	public ResponseEntity<BasicResponse> token(@RequestBody String accessToken){
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		User userJwt = null;

		try {
			userJwt = jwtService.checkJwt(accessToken);
			
			if (userJwt == null) {
				basicResponse.status = false;
				basicResponse.data = "Token Mismatch";
				responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			
			} else {
				basicResponse.status = true;
				basicResponse.data = "Token Match";
				basicResponse.object = userJwt;
				responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@PostMapping("/edit")
	@ApiOperation("정보수정")
	public ResponseEntity<BasicResponse> updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
}
