package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Favorite;
import com.daoumarket.dto.FavoriteDeleteReq;
import com.daoumarket.dto.ItemInfoRequest;
import com.daoumarket.dto.ItemResponse;

public interface IFavoriteDao {
	public int insertFavorite(Favorite favorite);
	public int deleteFavorite(FavoriteDeleteReq favorite);
	public List<ItemResponse> getFavoriteList(int userId);
	public boolean isFavorited(ItemInfoRequest itemInfoRequest);
}
