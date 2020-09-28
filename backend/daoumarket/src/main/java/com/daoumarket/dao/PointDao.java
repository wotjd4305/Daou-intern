package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.Rank;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PointDao implements IPointDao {
	
	private final SqlSession sqlSession;
	private String ns = "point.";

	@Override
	public List<Rank> getRank() {
		return sqlSession.selectList(ns + "getRank");
	}

}
