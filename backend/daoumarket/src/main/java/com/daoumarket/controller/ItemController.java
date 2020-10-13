package com.daoumarket.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInfoRequest;
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
	
	@GetMapping("/item/{itemId}")
	@ApiOperation("물건 상세정보 조회")
	public ResponseEntity<BasicResponse> getItemInfoByItemId(@PathVariable long itemId, @RequestParam int userId) {
		log.info("ItemController : getItemInfoByItemId => {}", itemId);
		
		ItemInfoRequest itemInfoRequest = ItemInfoRequest.builder()
				.itemId(itemId)
				.userId(userId).build();
		
		return itemService.getItemInfoByItemId(itemInfoRequest);
	}
	
	@PostMapping(path = "/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation("물건 등록")
	public ResponseEntity<BasicResponse> insertItem(@ModelAttribute ItemInsertRequest item,
													@RequestPart(required = false) MultipartFile[] images) {
		log.info("ItemController : insertItem");
		
		return itemService.insertItem(item, images);
	}
	
	@PatchMapping("/item/info")
	@ApiOperation("물건 정보 수정하기")
	public ResponseEntity<BasicResponse> updateItemInfo(@RequestBody ItemUpdateRequest item) {
		log.info("ItemController : updateItemInfo");
		
		return itemService.updateItemInfo(item);
	}
	
	@PatchMapping("/item/{itemId}/status")
	@ApiOperation("물건 상태 수정하기(판매중 = S, 거래중 = I, 거래완료 = C)")
	public ResponseEntity<BasicResponse> updateItemStatus(@PathVariable long itemId, @RequestParam char status) {
		log.info("ItemController : updateItemStatus");
		
		ItemUpdateRequest item = ItemUpdateRequest.builder()
				.itemId(itemId)
				.status(status).build();
		
		return itemService.updateItemStatus(item);
	}
	
	@DeleteMapping("/item/{itemId}")
	@ApiOperation("물건 삭제하기")
	public ResponseEntity<BasicResponse> deleteItem(@PathVariable long itemId) {
		log.info("ItemController : deleteItem");
		
		return itemService.deleteItem(itemId);
	}
	
	@GetMapping("/item")
	@ApiOperation("모든 물건 가져오기")
	public ResponseEntity<BasicResponse> getAllItems(@RequestParam int userId, @RequestParam int page) {
		log.info("ItemController : getAllItems");
		
		return itemService.getAllItems(userId, page);
	}
	
	@GetMapping("/item/category")
	@ApiOperation("선택된 카테고리의 물건 가져오기")
	public ResponseEntity<BasicResponse> getItemsByCategory(@RequestParam String[] category, @RequestParam int userId, @RequestParam int page) {
		log.info("ItemController : getItemsByCategory");
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.category(category)
				.userId(userId)
				.page(page).build();
		
		return itemService.getItemsByCategory(search);
	}
	
	@GetMapping("/item/keyword")
	@ApiOperation("키워드 물건 가져오기")
	public ResponseEntity<BasicResponse> getItemsByKeyword(@RequestParam(required = false) String[] category, @RequestParam(required = false) String keyword,
															@RequestParam int userId, @RequestParam int page) {
		log.info("ItemController : getItemsByKeyword");
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.category(category)
				.userId(userId)
				.keyword(keyword == null ? "" : keyword)
				.page(page).build();
		
		return itemService.getItemsByKeyword(search);
	}
	
	@GetMapping("/item/{userId}/list")
	@ApiOperation("userId의 물건 목록 가져오기")
	public ResponseEntity<BasicResponse> getItemsByUserId(@PathVariable int userId) {
		log.info("ItemController : getItemsByUserId => {}", userId);
		
		return itemService.getItemsByUserId(userId);
	}

}
