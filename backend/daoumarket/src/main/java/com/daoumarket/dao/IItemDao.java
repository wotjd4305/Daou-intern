package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

public interface IItemDao {
	public int getCount();
	public ItemResponse getItemById(long id);
	public int insertItem(ItemInsertRequest item);
	public int updateItemInfo(ItemUpdateRequest item);
	public int updateItemStatus(ItemUpdateRequest item);
	public int deleteItem(long id);
	public List<ItemResponse> getAllItems();
	public List<ItemResponse> getItemsByCategory(ItemSearchRequest search);
	public List<ItemResponse> getItemsByKeyword(ItemSearchRequest search);
	public List<ItemResponse> getItemsByCategoryAndKeyword(ItemSearchRequest search);
	public List<ItemResponse> getItemsByUserId(long id);
}
