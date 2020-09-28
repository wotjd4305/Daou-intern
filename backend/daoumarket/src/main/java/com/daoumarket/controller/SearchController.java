package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.SearchInsertResponse;
import com.daoumarket.service.ISearchService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SearchController {
	private final ISearchService searchService;
	
	@PostMapping("/search")
	@ApiOperation("키워드 검색")
	public ResponseEntity<BasicResponse> insertSearch(@RequestBody SearchInsertResponse searchInsertResponse){
		return searchService.insertSearch(searchInsertResponse);
	}
	
	@DeleteMapping("/search/{searchId}")
	@ApiOperation("키워드 삭제")
	public ResponseEntity<BasicResponse> deleteSearch(@PathVariable long searchId) {
		return searchService.deleteSearch(searchId);
	}
	
	@PostMapping("/search/get")
	@ApiOperation("키워드 탐색")
	public ResponseEntity<BasicResponse> getSearch(@RequestParam long userId){
		return searchService.getSearch(userId);
	}
}
