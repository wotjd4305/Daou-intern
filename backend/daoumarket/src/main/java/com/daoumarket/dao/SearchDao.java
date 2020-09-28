package com.daoumarket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

}
