package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.Image;

public interface IImageDao {
	public int insertItemImage(Image image);
	public int updateUserImage(Image image);
	public List<String> getItemImages(long id);
	public int deleteUserImage(int id);
}
