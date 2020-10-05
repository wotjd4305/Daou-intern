package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.Search;
import com.daoumarket.dto.SearchInsertResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SearchDao implements ISearchDao {

	private final SqlSession sqlSession;
	private String ns = "searchKeyword.";
	
	@Override
	public int insertSearch(SearchInsertResponse searchInsertResponse) {
		sqlSession.update(ns + "insertSearch", searchInsertResponse);
		return searchInsertResponse.getResult();
	}

	@Override
	public int deleteSearchHistory(long searchId) {
		return sqlSession.delete(ns + "deleteSearchHistory", searchId);
	}

	@Override
	public List<Search> getSearchHistory(int userId) {
		return sqlSession.selectList(ns + "getSearchHistory", userId);
	}
	
	

}
