package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Criteria;
import com.daoumarket.dto.ItemInfoRequest;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

public interface IItemDao {
	public int getCount();
	public ItemResponse getItemInfoByItemId(ItemInfoRequest itemInfoRequest);
	public int insertItem(ItemInsertRequest item);
	public int updateItemInfo(ItemUpdateRequest item);
	public int updateItemStatus(ItemUpdateRequest item);
	public int deleteItem(long itemId);
	public List<ItemResponse> getAllItems(Criteria cri);
	public List<ItemResponse> getItemsByCategory(ItemSearchRequest search);
	public List<ItemResponse> getItemsByKeyword(ItemSearchRequest search);
	public List<ItemResponse> getItemsByCategoryAndKeyword(ItemSearchRequest search);
	public List<ItemResponse> getItemsByUserId(long userId);
	public int getAllItemsCount();
	public int getItemsByCategoryCount(ItemSearchRequest search);
	public int getItemsByKeywordCount(ItemSearchRequest search);
	public int getItemsByCategoryAndKeywordCount(ItemSearchRequest search);
}
