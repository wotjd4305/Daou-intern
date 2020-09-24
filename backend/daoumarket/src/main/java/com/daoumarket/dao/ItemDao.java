package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSaveRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ItemDao implements IItemDao {

	private final SqlSession sqlSession;
	private String ns = "item.";
	
	@Override
	public ItemResponse getItemById(long id) {
		return sqlSession.selectOne(ns + "getItemById", id);
	}
	
	@Override
	public int insertItem(ItemSaveRequest item) {
		return sqlSession.insert(ns + "insertItem", item); 
	}

	@Override
	public int getCount() {
		return sqlSession.selectOne(ns + "getCount");
	}
}
