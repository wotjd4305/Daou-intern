package com.daoumarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IFavoriteDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Favorite;
import com.daoumarket.dto.FavoriteDeleteReq;
import com.daoumarket.dto.ItemInfoRequest;
import com.daoumarket.dto.ItemResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavoriteService implements IFavoriteService {
	
	private final IFavoriteDao favoriteDao;
	private final IImageService imageService;

	@Override
	public ResponseEntity<BasicResponse> insertFavorite(Favorite favorite) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		int res = favoriteDao.insertFavorite(favorite);
		
		if (res > 0) {
			response.isSuccess = true;
			response.message = "Success in Insert";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Faliure in Insert";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> deleteFavorite(FavoriteDeleteReq favorite) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		
		int res = favoriteDao.deleteFavorite(favorite);
		
		if (res > 0) {
			response.isSuccess = true;
			response.message = "Delete Completed";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Delete Failed";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> getFavoriteList(int userId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse response = new BasicResponse();
		List<ItemResponse> favoriteList = favoriteDao.getFavoriteList(userId);
		
		if (!favoriteList.isEmpty()) {
			for (ItemResponse item : favoriteList) {
				imageService.setItemImages(item);
			}
			response.isSuccess = true;
			response.message = "Favorite Extraction Completed";
			response.data = favoriteList;
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.isSuccess = false;
			response.message = "Favorite Extraction Failure";
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	@Override
    public void setItemIsFavorited(ItemResponse item, int userId) {
    	
		ItemInfoRequest itemInfoRequest = ItemInfoRequest.builder()
				.itemId(item.getItemId())
				.userId(userId).build();
		
		boolean isFavorite = favoriteDao.isFavorited(itemInfoRequest);
		
		if(isFavorite)
			item.setFavorite(true);
    }

}
