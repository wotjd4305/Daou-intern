package com.daoumarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.TokenRequest;
import com.daoumarket.dto.User;
import com.daoumarket.dto.UserLoginRequest;
import com.daoumarket.jwt.IJWTService;
import com.daoumarket.service.IImageService;
import com.daoumarket.service.IUserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {
	
	private final IUserService userService;
	private final IJWTService jwtService;
	private final IImageService imageService;
	
	@PostMapping("/user/join")
	@ApiOperation("회원가입")
	public ResponseEntity<BasicResponse> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	
	@GetMapping("/user/{empnum}")
	@ApiOperation("사번 중복 체크")
	public ResponseEntity<BasicResponse> getEmpNum(@PathVariable long empnum) {
		return userService.getEmpNum(empnum);
	}
	
	@PostMapping("/user/login")
	@ApiOperation("로그인")
	public ResponseEntity<BasicResponse> getUserLogin(@RequestBody UserLoginRequest userLogin){
		return userService.getUserLogin(userLogin);
	}
	
	@PostMapping("/user/token")
	@ApiOperation("토큰 검증")
	public ResponseEntity<BasicResponse> token(@RequestBody TokenRequest accessToken){
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
	
	@PatchMapping("/user/edit")
	@ApiOperation("회원 정보(비밀번호, 부서) 수정하기")
	public ResponseEntity<BasicResponse> updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	@PatchMapping("/user/{id}/image")
	@ApiOperation("유저 프로필 사진 변경하기")
	public ResponseEntity<BasicResponse> updateUserImage(@RequestPart MultipartFile image, @PathVariable long id) {
		log.info("userController : updateUserImage");
		
		return imageService.updateUserImage(image, id);
	}
	
	@DeleteMapping("/user/{id}/image")
	@ApiOperation("유저 프로필 사진 삭제하기")
	public ResponseEntity<BasicResponse> deleteUserImage(@PathVariable long id) {
		log.info("userController : deleteUserImage");
		
		return imageService.deleteUserImage(id);
	}
	
}
