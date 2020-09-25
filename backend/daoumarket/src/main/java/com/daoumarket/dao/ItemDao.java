package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ItemInsertRequestDto;
import com.daoumarket.dto.ItemResponseDto;
import com.daoumarket.dto.ItemUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ItemDao implements IItemDao {

	private final SqlSession sqlSession;
	private String ns = "item.";
	
	@Override
	public int getCount() {
		return sqlSession.selectOne(ns + "getCount");
	}
	
	@Override
	public ItemResponseDto getItemById(long id) {
		return sqlSession.selectOne(ns + "getItemById", id);
	}
	
	@Override
	public int insertItem(ItemInsertRequestDto item) {
		return sqlSession.insert(ns + "insertItem", item); 
	}

	@Override
	public int updateItemInfo(ItemUpdateRequestDto item) {
		return sqlSession.update(ns + "updateItem", item);
	}

	@Override
	public int updateItemStatus(ItemUpdateRequestDto item) {
		return sqlSession.update(ns + "updateStatusItem", item);
	}
}
