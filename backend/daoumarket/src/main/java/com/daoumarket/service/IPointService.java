package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;

public interface IPointService {
	public ResponseEntity<BasicResponse> getRank();
}
