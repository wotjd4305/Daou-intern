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
		BasicResponse basicResponse = new BasicResponse();
		List<Rank> rank = null;
		
		rank = pointDao.getRank();
		
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
