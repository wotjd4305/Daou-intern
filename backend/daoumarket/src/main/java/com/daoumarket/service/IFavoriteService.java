package com.daoumarket.service;

import org.springframework.http.ResponseEntity;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Favorite;

public interface IFavoriteService {
	public ResponseEntity<BasicResponse> insertFavorite(Favorite favorite);
	public ResponseEntity<BasicResponse> deleteFavorite(long favoriteId);
	public ResponseEntity<BasicResponse> getFavoriteList(int userId);
}
