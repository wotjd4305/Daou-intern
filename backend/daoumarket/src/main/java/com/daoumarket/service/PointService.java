package com.daoumarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daoumarket.dao.IPointDao;
import com.daoumarket.dto.Rank;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PointService implements IPointService {
	
	private final IPointDao pointDao;

	@Override
	public List<Rank> getRank() {
		return pointDao.getRank();
	}

}
