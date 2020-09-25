package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Image;

public interface IImageDao {
	public int insertItemImage(Image image);
	public int insertUserImage(Image image);
	public List<Image> getImage(long id);
}
