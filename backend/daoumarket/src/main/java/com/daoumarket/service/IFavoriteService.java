package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Favorite;
import com.daoumarket.dto.FavoriteDeleteReq;
import com.daoumarket.dto.ItemResponse;

public interface IFavoriteService {
	public ResponseEntity<BasicResponse> insertFavorite(Favorite favorite);
	public ResponseEntity<BasicResponse> deleteFavorite(FavoriteDeleteReq favorite);
	public ResponseEntity<BasicResponse> getFavoriteList(int userId);
	public void setItemIsFavorited(ItemResponse item, int userId);
}
