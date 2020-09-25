package com.daoumarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.User;
import com.daoumarket.jwt.IJWTService;
import com.daoumarket.service.IUserService;
import com.daoumarket.util.EncodePassword;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {
	
	private final IUserService userService;
	private final IJWTService jwtService;
	
	// sign up
	@PostMapping("/user")
	@ApiOperation("ȸ������")
	public ResponseEntity<BasicResponse> insertUser(@RequestBody User dto) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		User encodePasswordDto = EncodePassword.Encode(dto);
		
		int res = userService.insertUser(encodePasswordDto);

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
	
	
	@GetMapping("/{num}")
	@ApiOperation("���̵� �ߺ� üũ")
	public ResponseEntity<BasicResponse> getNum(@PathVariable long num) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		User res = userService.getNum(num);
		
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
	@PostMapping("/login")
	@ApiOperation("�α���")
	public ResponseEntity<BasicResponse> getUserLogin(@RequestBody User dto){
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		User encodePasswordDto = EncodePassword.Encode(dto);
		User res = userService.getUserLogin(encodePasswordDto);
		
		if (res == null) {
			basicResponse.status = false;
			basicResponse.data = "Discorrect";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = true;
			basicResponse.data = "Correct";
			
			try {
				String token = jwtService.makeJwt(res);
				
				basicResponse.object = token;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);		
		}
		
		return responseEntity;
	}
	
	@PostMapping("/token")
	@ApiOperation("��ū ����")
	public ResponseEntity<BasicResponse> token(@RequestBody String accessToken){
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		User jwt = null;

		try {
			jwt = jwtService.checkJwt(accessToken);
			
			if (jwt == null) {
				basicResponse.status = false;
				basicResponse.data = "Token Mismatch";
				responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			
			} else {
				basicResponse.status = true;
				basicResponse.data = "Token Match";
				basicResponse.object = jwt;
				responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	// edit user
	@PostMapping("/edit")
	@ApiOperation("��������")
	public ResponseEntity<BasicResponse> updateUser(@RequestBody User dto){
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		User encodePasswordDto = EncodePassword.Encode(dto);
		int res = userService.updateUser(encodePasswordDto);
		
		if (res > 0) {
			
			try {
				String token = jwtService.makeJwt(encodePasswordDto);
				
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
