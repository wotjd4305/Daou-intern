package com.daoumarket.dao;

import com.daoumarket.dto.ItemResponseDto;
import com.daoumarket.dto.ItemUpdateRequestDto;
import com.daoumarket.dto.ItemInsertRequestDto;

public interface IItemDao {
	public int getCount();
	public ItemResponseDto getItemById(long id);
	public int insertItem(ItemInsertRequestDto item);
	public int updateItemInfo(ItemUpdateRequestDto item);
	public int updateItemStatus(ItemUpdateRequestDto item);
}
