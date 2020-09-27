package com.daoumarket.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Rank;
import com.daoumarket.service.IPointService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class PointController {
	
	private final IPointService pointService;
	
	@GetMapping("/point/rank")
	@ApiOperation("순위 호출")
	public ResponseEntity<BasicResponse> getRank(){
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		List<Rank> rank = null;
		
		rank = pointService.getRank();
		
		if (rank != null) {
			basicResponse.status = true;
			basicResponse.data = "Rank Extraction Completed";
			basicResponse.object = rank;
			
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Rank Extraction Failure";
			
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
}
