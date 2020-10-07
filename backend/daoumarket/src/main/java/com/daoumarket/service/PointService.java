package com.daoumarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IPointDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Rank;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PointService implements IPointService {
	
	private final IPointDao pointDao;

	@Override
	public ResponseEntity<BasicResponse> getRank() {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		List<Rank> rank = null;
		
		rank = pointDao.getRank();
		
		if (rank != null) {
			response.isSuccess = true;
			response.message = "Rank Extraction Completed";
			response.data = rank;
			
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Rank Extraction Failure";
			
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

}
