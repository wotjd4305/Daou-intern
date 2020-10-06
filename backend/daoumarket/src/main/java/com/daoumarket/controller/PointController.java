package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.service.IPointService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class PointController {
	
	private final IPointService pointService;
	
	@GetMapping("/point/rank")
	@ApiOperation("순위 호출")
	public ResponseEntity<BasicResponse> getRank(){
		log.info("PointController : getRank");
		
		return pointService.getRank();
	}
	
}
