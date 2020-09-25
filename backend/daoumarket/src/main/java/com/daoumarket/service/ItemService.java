package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemInsertRequestDto;
import com.daoumarket.dto.ItemUpdateRequestDto;

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
	public ResponseEntity<BasicResponse> insertItem(ItemInsertRequestDto item) {
		
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
	public ResponseEntity<BasicResponse> updateItemInfo(ItemUpdateRequestDto item) {
		
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
	public ResponseEntity<BasicResponse> updateItemStatus(ItemUpdateRequestDto item) {
		
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
}
