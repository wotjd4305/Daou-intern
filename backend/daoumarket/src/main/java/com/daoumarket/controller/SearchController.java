package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SearchController {
	private final ISearchService searchService;
	
	@PostMapping("/search")
	@ApiOperation("키워드 검색")
	public ResponseEntity<BasicResponse> insertSearch(@RequestBody SearchInsertResponse searchInsertResponse){
		log.info("SearchController : insertSearch");
		
		return searchService.insertSearch(searchInsertResponse);
	}
	
	@DeleteMapping("/search/{searchId}")
	@ApiOperation("검색 id를 이용해서 키워드 삭제")
	public ResponseEntity<BasicResponse> deleteSearchHistory(@PathVariable long searchId) {
		log.info("SearchController : deleteSearchHistory => {}", searchId);
		
		return searchService.deleteSearchHistory(searchId);
	}
	
	@GetMapping("/search/{userId}")
	@ApiOperation("유저 id를 이용해서 검색 이력 확인")
	public ResponseEntity<BasicResponse> getSearchHistory(@RequestParam int userId){
		log.info("SearchController : getSearchHistory => {}", userId);
		
		return searchService.getSearchHistory(userId);
	}
}
