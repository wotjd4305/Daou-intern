package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;

public interface ICategoryService {
	public ResponseEntity<BasicResponse> getItemCategory();
	public ResponseEntity<BasicResponse> getDepartmentCategory();
}
