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
		BasicResponse response = new BasicResponse();
		
		int res = searchDao.insertSearch(searchInsertResponse);
		
		if (res > 0) {
			response.isSuccess = true;
			response.message = "Success in Insert";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Faliure in Insert";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> deleteSearchHistory(long searchId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		int res = searchDao.deleteSearchHistory(searchId);
		
		if (res > 0) {
			response.isSuccess = true;
			response.message = "Delete Completed";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Delete Failed";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> getSearchHistory(int userId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		List<Search> search = null;
		
		search = searchDao.getSearchHistory(userId);
		
		if (search != null) {
			response.isSuccess = true;
			response.message = "Search Extraction Completed";
			response.data = search;
			
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Search Extraction Failure";
			
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

}
