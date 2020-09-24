package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemSaveRequest;

public interface IItemService {
	public ResponseEntity<BasicResponse> getItemById(long id);
	public ResponseEntity<BasicResponse> insertItem(ItemSaveRequest item);
	public ResponseEntity<BasicResponse> getCategory();
}
