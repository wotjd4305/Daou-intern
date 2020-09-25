package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService implements IItemService {

	private final IItemDao itemDao;
	
	@Override
	public ResponseEntity<BasicResponse> getItemById(long id) {
		
		BasicResponse response = new BasicResponse();
		
		response.object = itemDao.getItemById(id);
		
		if(response.object != null) {
			response.status = true;
			response.data = "데이터 존재";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "데이터가 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> insertItem(ItemInsertRequest item) {
		
		BasicResponse response = new BasicResponse();
		
		int result = itemDao.insertItem(item);
		
		if(result == 1) {
			response.status = true;
			response.data = "저장 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "저장 실패";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
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
		
		response.object = itemDao.getAllItems();
		
		if(response.object != null) {
			response.status = true;
			response.data = "물건 가져오기 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByCategory(ItemSearchRequest search) {
		
		BasicResponse response = new BasicResponse();
		
		response.object = itemDao.getItemsByCategory(search);
		
		if(response.object != null) {
			response.status = true;
			response.data = "물건 가져오기 성공";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		response.data = "물건이 존재하지 않음";
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<BasicResponse> getItemsByKeyword(ItemSearchRequest search) {
		
		BasicResponse response = new BasicResponse();
		
		if(search.getCategory().length == 0) { // 카테고리가 선택되어 있지 않은 경우
			response.object = itemDao.getItemsByKeyword(search);
			if(response.object != null) {
				response.status = true;
				response.data = "물건 가져오기 성공";
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			response.data = "물건이 존재하지 않음";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else { // 카테고리가 선택되어 있는 경우
			response.object = itemDao.getItemsByCategoryAndKeyword(search);
			if(response.object != null) {
				response.status = true;
				response.data = "물건 가져오기 성공";
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			response.data = "물건이 존재하지 않음";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
