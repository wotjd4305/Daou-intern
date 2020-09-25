package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInsertRequestDto;
import com.daoumarket.dto.ItemUpdateRequestDto;

public interface IItemService {
	public ResponseEntity<BasicResponse> getItemById(long id);
	public ResponseEntity<BasicResponse> insertItem(ItemInsertRequestDto item);
	public ResponseEntity<BasicResponse> updateItemInfo(ItemUpdateRequestDto item);
	public ResponseEntity<BasicResponse> updateItemStatus(ItemUpdateRequestDto item);
}
