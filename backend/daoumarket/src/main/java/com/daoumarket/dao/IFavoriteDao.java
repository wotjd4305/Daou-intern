package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Favorite;
import com.daoumarket.dto.ItemInfoRequest;

public interface IFavoriteDao {
	public int insertFavorite(Favorite favorite);
	public int deleteFavorite(long favoriteId);
	public List<Favorite> getFavoriteList(int userId);
	public boolean isFavorited(ItemInfoRequest itemInfoRequest);
}
