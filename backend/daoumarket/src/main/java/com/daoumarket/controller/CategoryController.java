package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.service.ICategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CategoryController {
	
	private final ICategoryService categoryService;
	
	@GetMapping("/category")
	@ApiOperation("카테고리 가져오기")
	public ResponseEntity<BasicResponse> getCategory() {
		log.info("CategoryController : getCategory");
		
		return categoryService.getCategory();
	}
}
