package com.daoumarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.ISearchDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Search;
import com.daoumarket.dto.SearchInsertResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchService implements ISearchService {
	
	public final ISearchDao searchDao;

	@Override
	public ResponseEntity<BasicResponse> insertSearch(SearchInsertResponse searchInsertResponse) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		int res = searchDao.insertSearch(searchInsertResponse);
		
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

	@Override
	public ResponseEntity<BasicResponse> deleteSearch(long searchId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		int res = searchDao.deleteSearch(searchId);
		
		if (res > 0) {
			basicResponse.status = true;
			basicResponse.data = "Delete Completed";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Delete Failed";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> getSearch(long userId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		List<Search> search = null;
		
		search = searchDao.getSearch(userId);
		
		if (search != null) {
			basicResponse.status = true;
			basicResponse.data = "Search Extraction Completed";
			basicResponse.object = search;
			
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Search Extraction Failure";
			
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}

}
