package com.daoumarket.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService implements IItemService {

	private final IItemDao itemDao;
	private final IImageService imageService;
	
	@Override
	public ResponseEntity<BasicResponse> getItemById(long id) {
		
		BasicResponse response = new BasicResponse();
		
		response.object = itemDao.getItemById(id);
		
		if(response.object != null) {
			response.status = true;
			response.data = "데이터 존재";
			imageService.getItemImages((ItemResponse)response.object);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "데이터가 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@Override
	public ResponseEntity<BasicResponse> insertItem(ItemInsertRequest item, MultipartFile[] images) {
		
		BasicResponse response = new BasicResponse();
		int resultCnt = 0;
		int id = itemDao.insertItem(item);
		
		if(id == 0) { // 게시물 등록 실패한 경우
			response.data = "게시물 등록 실패";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		resultCnt++; 
		if(images.length > 0) { // 이미지 파일이 존재하면 이미지 업로드 진행
			resultCnt += imageService.insertItemImage(images, id);
		} else { // 이미지 없는 게시물 등록
			response.status = true;
			response.data = "게시물 등록 성공!(이미지 파일은 없음)";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		if(resultCnt == 1) {
			response.data = "게시물은 등록 되었으나, 이미지 파일 업로드 실패";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		response.status = true;
		response.data = "게시물과 이미지 등록 성공!";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Transactional
	@Override
	public ResponseEntity<BasicResponse> updateItemInfo(ItemUpdateRequest item) {
		
		BasicResponse response = new BasicResponse();
		
		int result = itemDao.updateItemInfo(item);
		
		if(result == 1) {
			response.status = true;
			response.data = "물건 정보 수정 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건 정보 수정 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@Transactional
	@Override
	public ResponseEntity<BasicResponse> updateItemStatus(ItemUpdateRequest item) {
		
		BasicResponse response = new BasicResponse();
		
		int result = itemDao.updateItemStatus(item);
		
		if(result == 1) {
			response.status = true;
			response.data = "물건 상태 수정 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건 상태 수정 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@Override
	public ResponseEntity<BasicResponse> deleteItem(long id) {
		
		BasicResponse response = new BasicResponse();
		
		int result = itemDao.deleteItem(id);
		
		if(result == 1) {
			response.status = true;
			response.data = "물건 삭제 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건 삭제 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getAllItems() {
		
		BasicResponse response = new BasicResponse();
		
		List<ItemResponse> items = itemDao.getAllItems();
		
		if(items != null) {
			for (ItemResponse item : items) {
				imageService.getItemImages(item);
			}
			response.status = true;
			response.data = "물건 가져오기 성공";
			response.object = items;
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByCategory(ItemSearchRequest search) {
		
		BasicResponse response = new BasicResponse();
		
		
		List<ItemResponse> items = itemDao.getItemsByCategory(search);
		
		if(items != null) {
			for (ItemResponse item : items) {
				imageService.getItemImages(item);
			}
			response.status = true;
			response.data = "물건 가져오기 성공";
			response.object = items;
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByKeyword(ItemSearchRequest search) {
		
		BasicResponse response = new BasicResponse();
		
		if(search.getCategory() == null) { // 카테고리가 선택되어 있지 않은 경우
			List<ItemResponse> items = itemDao.getItemsByKeyword(search);
			if(items != null) {
				for (ItemResponse item : items) {
					imageService.getItemImages(item);
				}
				response.status = true;
				response.data = "물건 가져오기 성공";
				response.object = items;
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			response.data = "물건이 존재하지 않음";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else { // 카테고리가 선택되어 있는 경우
			List<ItemResponse> items = itemDao.getItemsByCategoryAndKeyword(search);
			if(items != null) {
				for (ItemResponse item : items) {
					imageService.getItemImages(item);
				}
				response.status = true;
				response.data = "물건 가져오기 성공";
				response.object = items;
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			response.data = "물건이 존재하지 않음";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByUserId(long id) {
		BasicResponse response = new BasicResponse();
		
		List<ItemResponse> items = itemDao.getItemsByUserId(id);
		
		if(items != null) {
			for (ItemResponse item : items) {
				imageService.getItemImages(item);
			}
			response.status = true;
			response.data = "물건 가져오기 성공";
			response.object = items;
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
