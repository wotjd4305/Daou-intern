package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Search;
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
	public ResponseEntity<BasicResponse> insertSearch(@RequestBody Search search){
		return searchService.insertSearch(search);
	}
}
