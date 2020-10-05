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
	
	@GetMapping("/category/item")
	@ApiOperation("물건 카테고리 가져오기")
	public ResponseEntity<BasicResponse> getItemCategory() {
		log.info("CategoryController : getItemCategory");
		
		return categoryService.getItemCategory();
	}
	
	@GetMapping("/category/department")
	@ApiOperation("부서 카테고리 가져오기")
	public ResponseEntity<BasicResponse> getDepartmentCategory() {
		log.info("CategoryController : getDepartmentCategory");
		
		return categoryService.getDepartmentCategory();
	}
}
