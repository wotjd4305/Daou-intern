package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.service.IItemService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ItemController {
	
	private final IItemService itemService;
	
	@GetMapping("/item/{id}")
	@ApiOperation("물건 상세정보 조회")
	public ResponseEntity<BasicResponse> getItemById(@PathVariable long id) {
		log.info("ItemController : getItemById");
		
		return itemService.getItemById(id);
	}
}
