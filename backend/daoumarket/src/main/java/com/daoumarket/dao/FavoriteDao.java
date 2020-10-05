package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.Favorite;
import com.daoumarket.dto.ItemInfoRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class FavoriteDao implements IFavoriteDao {

	private final SqlSession sqlSession;
	private String ns = "favorite.";
	
	@Override
	public int insertFavorite(Favorite favorite) {
		return sqlSession.update(ns + "insertFavorite", favorite);
	}

	@Override
	public int deleteFavorite(long favoriteId) {
		return sqlSession.delete(ns + "deleteFavorite", favoriteId);
	}

	@Override
	public List<Favorite> getFavoriteList(int userId) {
		return sqlSession.selectList(ns + "getFavoriteList", userId);
	}

	@Override
	public boolean isFavorited(ItemInfoRequest itemInfoRequest) {
		return sqlSession.selectOne(ns + "isFavorited", itemInfoRequest);
	}
	
	

}
