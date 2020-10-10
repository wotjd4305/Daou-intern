package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.Image;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ImageDao implements IImageDao {
	
	private String ns = "image.";
	
	private final SqlSession sqlSession;
	
	@Override
	public int insertItemImage(Image image) {
		return sqlSession.insert(ns + "insertItemImage", image);
	}

	@Override
	public int updateUserImage(Image image) {
		return sqlSession.update(ns + "updateUserImage", image);
	}
	
	@Override
	public List<String> getItemImages(long id) {
		return sqlSession.selectList(ns + "getItemImages", id);
	}

	@Override
	public int deleteUserImage(int id) {
		return sqlSession.update(ns + "deleteUserImage", id);
	}
	
}
