package com.daoumarket.dao;

import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSaveRequest;

public interface IItemDao {

	public ItemResponse getItemById(long id);
	public int insertItem(ItemSaveRequest item);
	public int getCount();
}
