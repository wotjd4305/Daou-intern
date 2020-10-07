package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dto.BasicResponse;

@Service
public class CategoryService implements ICategoryService {

	@Override
	public ResponseEntity<BasicResponse> getItemCategory() {
		
		BasicResponse response = new BasicResponse();
		
		String[] category = {"디지털/가전", "가구/인테리어", "유아동/유아도서", "생활/가공식품", 
				"스포츠/레저", "여성잡화", "여성의류", "남성패션/잡화", 
				"게임/취미", "뷰티/미용", "반려동물용품", "도서/티켓/음반", "기타", "무료나눔"};
		
		response.isSuccess = true;
		response.message = "물건 카테고리 호출 성공";
		response.data = category;
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BasicResponse> getDepartmentCategory() {
		
		BasicResponse response = new BasicResponse();
		
		String[] category = {"서비스개발본부", "비즈메시징사업본부", "비즈마케팅사업본부", "커머스사업본부", 
				"고객지원실", "기타"};
		
		response.isSuccess = true;
		response.message = "부서 카테고리 호출 성공";
		response.data = category;
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
