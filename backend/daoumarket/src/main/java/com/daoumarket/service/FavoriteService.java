package com.daoumarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IFavoriteDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Favorite;
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
		BasicResponse basicResponse = new BasicResponse();
		
		int res = favoriteDao.insertFavorite(favorite);
		
		if (res > 0) {
			basicResponse.status = true;
			basicResponse.data = "Success in Insert";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Faliure in Insert";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> deleteFavorite(long favoriteId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		
		int res = favoriteDao.deleteFavorite(favoriteId);
		
		if (res > 0) {
			basicResponse.status = true;
			basicResponse.data = "Delete Completed";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Delete Failed";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<BasicResponse> getFavoriteList(int userId) {
		ResponseEntity<BasicResponse> responseEntity = null;
		BasicResponse basicResponse = new BasicResponse();
		List<ItemResponse> favoriteList = favoriteDao.getFavoriteList(userId);
		
		if (!favoriteList.isEmpty()) {
			for (ItemResponse item : favoriteList) {
				imageService.setItemImages(item);
			}
			basicResponse.status = true;
			basicResponse.data = "Favorite Extraction Completed";
			basicResponse.object = favoriteList;
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
		} else {
			basicResponse.status = false;
			basicResponse.data = "Favorite Extraction Failure";
			responseEntity = new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
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
