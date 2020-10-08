package com.daoumarket.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInfoRequest;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

public interface IItemService {
	public ResponseEntity<BasicResponse> getItemInfoByItemId(ItemInfoRequest itemInfoRequest);
	public ResponseEntity<BasicResponse> insertItem(ItemInsertRequest item, MultipartFile[] images);
	public ResponseEntity<BasicResponse> updateItemInfo(ItemUpdateRequest item);
	public ResponseEntity<BasicResponse> updateItemStatus(ItemUpdateRequest item);
	public ResponseEntity<BasicResponse> deleteItem(long itemId);
	public ResponseEntity<BasicResponse> getAllItems(int userId, int page);
	public ResponseEntity<BasicResponse> getItemsByCategory(ItemSearchRequest search);
	public ResponseEntity<BasicResponse> getItemsByKeyword(ItemSearchRequest search);
	public ResponseEntity<BasicResponse> getItemsByUserId(int userId);
}
