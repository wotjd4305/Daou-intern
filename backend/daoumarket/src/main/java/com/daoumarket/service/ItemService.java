package com.daoumarket.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Criteria;
import com.daoumarket.dto.ItemInfoRequest;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;
import com.daoumarket.dto.PageMaker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService implements IItemService {

	private final IItemDao itemDao;
	private final IImageService imageService;
	private final IFavoriteService favoriteService;
	
	@Override
	public ResponseEntity<BasicResponse> getItemInfoByItemId(ItemInfoRequest itemInfoRequest) {
		
		BasicResponse response = new BasicResponse();
		
		ItemResponse item = itemDao.getItemInfoByItemId(itemInfoRequest);
		
		if(item != null) {
			imageService.setItemImages(item);
			favoriteService.setItemIsFavorited(item, itemInfoRequest.getUserId());
			response.isSuccess = true;
			response.message = "물건 정보를 가져옴";
			response.data = item;
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건을 찾을 수 없음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@Override
	public ResponseEntity<BasicResponse> insertItem(ItemInsertRequest item, MultipartFile[] images) {
		
		BasicResponse response = new BasicResponse();
		int resultCnt = (images.length == 0) ? 0 : 2;
		int id = itemDao.insertItem(item);
		
		if(id > 0) {
			resultCnt++; 
			if(images.length > 0 && imageService.insertItemImage(images, id) > 0) { // 이미지 파일이 존재하면 이미지 업로드 진행
				resultCnt++;
			}
		}
		
		switch (resultCnt) {
		case 0:
			// 게시물 등록 실패(이미지 파일 X)
			response.message = "게시물 등록 실패(이미지 파일 X)";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		case 1:
			// 게시물 등록 성공(이미지 파일 X)
			response.isSuccess = true;
			response.message = "게시물 등록 성공!(이미지 파일 X)";
			return new ResponseEntity<>(response, HttpStatus.OK);
		case 2:
			// 게시물 등록 실패(이미지 파일 O)
			response.message = "게시물 등록 실패(이미지 파일 O)";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		case 3:
			// 게시물 등록 성공했으나, 이미지 등록 실패(이미지 파일 O)
			response.message = "게시물 등록 성공했으나, 이미지 등록 실패(이미지 파일 O)";
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		default :
			response.isSuccess = true;
			response.data = "게시물과 이미지 등록 성공!(이미지 파일 O)";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@Transactional
	@Override
	public ResponseEntity<BasicResponse> updateItemInfo(ItemUpdateRequest item) {
		
		BasicResponse response = new BasicResponse();
		
		if(itemDao.updateItemInfo(item) == 1) {
			response.isSuccess = true;
			response.message = "물건 정보 수정 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건 정보 수정 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@Transactional
	@Override
	public ResponseEntity<BasicResponse> updateItemStatus(ItemUpdateRequest item) {
		
		BasicResponse response = new BasicResponse();
		
		if(itemDao.updateItemStatus(item) == 1) {
			response.isSuccess = true;
			response.message = "물건 상태 수정 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건 상태 수정 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@Override
	public ResponseEntity<BasicResponse> deleteItem(long itemId) {
		
		BasicResponse response = new BasicResponse();
		
		if(itemDao.deleteItem(itemId) == 1) {
			response.isSuccess = true;
			response.message = "물건 삭제 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건 삭제 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getAllItems(int userId, int page) {
		
		BasicResponse response = new BasicResponse();
		
		Criteria cri = new Criteria(page);
		
		List<ItemResponse> items = itemDao.getAllItems(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		if(!items.isEmpty()) {
			pageMaker.setTotalCount(itemDao.getAllItemsCount());
			for (ItemResponse item : items) {
				imageService.setItemImages(item);
				favoriteService.setItemIsFavorited(item, userId);
			}
			response.isSuccess = true;
			response.message = "물건 가져오기 성공";
			response.data = items;
			response.pageMaker = pageMaker;
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByCategory(ItemSearchRequest search) {
		
		BasicResponse response = new BasicResponse();
		
		Criteria cri = new Criteria(search.getPage());
		search.setCri(cri);
		
		List<ItemResponse> items = itemDao.getItemsByCategory(search);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		if(!items.isEmpty()) {
			pageMaker.setTotalCount(itemDao.getItemsByCategoryCount(search));
			for (ItemResponse item : items) {
				imageService.setItemImages(item);
				favoriteService.setItemIsFavorited(item, search.getUserId());
			}
			response.isSuccess = true;
			response.message = "물건 가져오기 성공";
			response.data = items;
			response.pageMaker = pageMaker;
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByKeyword(ItemSearchRequest search) {
		
		BasicResponse response = new BasicResponse();
		
		Criteria cri = new Criteria(search.getPage());
		search.setCri(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		if(search.getCategory() == null) { // 카테고리가 선택되어 있지 않은 경우
			List<ItemResponse> items = itemDao.getItemsByKeyword(search);
			if(!items.isEmpty()) {
				pageMaker.setTotalCount(itemDao.getItemsByKeywordCount(search));
				for (ItemResponse item : items) {
					imageService.setItemImages(item);
					favoriteService.setItemIsFavorited(item, search.getUserId());
				}
				response.isSuccess = true;
				response.message = "물건 가져오기 성공";
				response.data = items;
				response.pageMaker = pageMaker;
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			response.message = "물건이 존재하지 않음";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else { // 카테고리가 선택되어 있는 경우
			List<ItemResponse> items = itemDao.getItemsByCategoryAndKeyword(search);
			if(!items.isEmpty()) {
				pageMaker.setTotalCount(itemDao.getItemsByCategoryAndKeywordCount(search));
				for (ItemResponse item : items) {
					imageService.setItemImages(item);
					favoriteService.setItemIsFavorited(item, search.getUserId());
				}
				response.isSuccess = true;
				response.message = "물건 가져오기 성공";
				response.data = items;
				response.pageMaker = pageMaker;
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			response.message = "물건이 존재하지 않음";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByUserId(int userId) {
		BasicResponse response = new BasicResponse();
		
		List<ItemResponse> items = itemDao.getItemsByUserId(userId);
		
		if(!items.isEmpty()) {
			for (ItemResponse item : items) {
				imageService.setItemImages(item);
				favoriteService.setItemIsFavorited(item, userId);
			}
			response.isSuccess = true;
			response.message = "물건 가져오기 성공";
			response.data = items;
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.message = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
