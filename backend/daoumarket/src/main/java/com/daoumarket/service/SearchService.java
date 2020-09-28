package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.ISearchDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Search;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchService implements ISearchService {
	
	public final ISearchDao searchDao;

	@Override
	public ResponseEntity<BasicResponse> insertSearch(Search search) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		System.out.println(search);
		
		int res = searchDao.insertSearch(search);
		
		if (res > 0) {
			basicResponse.status = true;
			basicResponse.data = "Success in Insert";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Faliure in Insert";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}

}
