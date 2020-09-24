package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;

public interface IItemService {
	public ResponseEntity<BasicResponse> getItemById(long id);
}
