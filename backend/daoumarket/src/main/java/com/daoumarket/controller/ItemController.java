package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;
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
		log.info("ItemController : getItemById => {id}", id);
		
		return itemService.getItemById(id);
	}
	
	@PostMapping("/item")
	@ApiOperation("물건 등록")
	public ResponseEntity<BasicResponse> insertItem(@RequestParam long userId, @RequestParam int price,
													@RequestParam String category, @RequestParam String content) {
		log.info("ItemController : insertItem");
		
		ItemInsertRequest item = ItemInsertRequest.builder()
				.userId(userId)
				.price(price)
				.category(category)
				.content(content).build();
		
		return itemService.insertItem(item);
	}
	
	@PatchMapping("/item/info")
	@ApiOperation("물건 정보 수정하기")
	public ResponseEntity<BasicResponse> updateItemInfo(ItemUpdateRequest item) {
		log.info("ItemController : updateItemInfo");
		
		return itemService.updateItemInfo(item);
	}
	
	@PatchMapping("/item/status")
	@ApiOperation("물건 상태 수정하기(판매중, 예약중, 판매완료)")
	public ResponseEntity<BasicResponse> updateItemStatus(ItemUpdateRequest item) {
		log.info("ItemController : updateItemStatus");
		
		return itemService.updateItemStatus(item);
	}
	
	@DeleteMapping("/item/{id}")
	@ApiOperation("물건 삭제하기")
	public ResponseEntity<BasicResponse> deleteItem(long id) {
		log.info("ItemController : deleteItem");
		
		return itemService.deleteItem(id);
	}
	
	@GetMapping("/item")
	@ApiOperation("모든 물건 가져오기")
	public ResponseEntity<BasicResponse> getAllItems() {
		log.info("ItemController : getAllItems");
		
		return itemService.getAllItems();
	}
	
	@GetMapping("/item/category")
	@ApiOperation("선택된 카테고리의 물건 가져오기")
	public ResponseEntity<BasicResponse> getItemsByCategory(ItemSearchRequest search) {
		log.info("ItemController : getItemsByCategory");
		
		return itemService.getItemsByCategory(search);
	}
	
//	@GetMapping("/item/category")
//	@ApiOperation("카테고리로 물건 가져오기")
//	public ResponseEntity<BasicResponse> getItemsByKeyword(ItemSearchRequest search) {
//		log.info("ItemController : getItemsByKeyword");
//		
//		return itemService.getItemsByKeyword(search);
//	}
}
