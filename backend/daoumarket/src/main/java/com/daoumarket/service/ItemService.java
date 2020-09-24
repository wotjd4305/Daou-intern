package com.daoumarket.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.BasicResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService implements IItemService {

	private final IItemDao itemDao;
	
	@Override
	public ResponseEntity<BasicResponse> getItemById(long id) {
		
		BasicResponse result = new BasicResponse();
		result.object = itemDao.getItemById(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
