package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemSaveRequest;

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
	public ResponseEntity<BasicResponse> insertItem(ItemSaveRequest item) {
		
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
	public ResponseEntity<BasicResponse> getCategory() {
		
		BasicResponse response = new BasicResponse();
		
		String[] category = {"디지털/가전", "가구/인테리어", "유아동/유아도서", "생활/가공식품", 
				"스포츠/레저", "여성잡화", "여성의류", "남성패션/잡화", 
				"게임/취미", "뷰티/미용", "반려동물용품", "도서/티켓/음반", "기타", "무료나눔"};
		
		response.data = "가져오기 성공";
		response.object = category;
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
