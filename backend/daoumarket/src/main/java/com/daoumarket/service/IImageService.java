package com.daoumarket.service;

import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.ItemResponse;

public interface IImageService {
	public int insertItemImage(MultipartFile[] images, long id);
	public int updateUserImage(MultipartFile image, long id);
	public void getItemImages(ItemResponse item);
}