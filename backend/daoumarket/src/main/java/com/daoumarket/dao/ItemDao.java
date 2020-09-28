package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

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
	public ItemResponse getItemById(long id) {
		return sqlSession.selectOne(ns + "getItemById", id);
	}
	
	@Override
	public int insertItem(ItemInsertRequest item) {
		return sqlSession.insert(ns + "insertItem", item); 
	}

	@Override
	public int updateItemInfo(ItemUpdateRequest item) {
		return sqlSession.update(ns + "updateItem", item);
	}

	@Override
	public int updateItemStatus(ItemUpdateRequest item) {
		return sqlSession.update(ns + "updateStatusItem", item);
	}

	@Override
	public int deleteItem(long id) {
		return sqlSession.delete(ns + "deleteItem", id);
	}
	
	@Override
	public List<ItemResponse> getAllItems() {
		return sqlSession.selectList(ns + "getAllItems");
	}

	@Override
	public List<ItemResponse> getItemsByCategory(ItemSearchRequest search) {
		return sqlSession.selectList(ns + "getItemsByCategory", search);
	}

	@Override
	public List<ItemResponse> getItemsByKeyword(ItemSearchRequest search) {
		return sqlSession.selectList(ns + "getItemsByKeyword", search);
	}

	@Override
	public List<ItemResponse> getItemsByCategoryAndKeyword(ItemSearchRequest search) {
		return sqlSession.selectList(ns + "getItemsByCategoryAndKeyword", search);
	}

	@Override
	public List<ItemResponse> getItemsByUserId(long userId) {
		return sqlSession.selectList(ns + "getItemsByUserId", userId);
	}

}
