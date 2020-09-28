package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Search;
import com.daoumarket.dto.SearchInsertResponse;

public interface ISearchDao {
	public int insertSearch(SearchInsertResponse searchInsertResponse);
	public int deleteSearch(long searchId);
	public List<Search> getSearch(long userId);
}
