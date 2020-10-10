package com.daoumarket.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemResponse;

public interface IImageService {
	public int insertItemImage(MultipartFile[] images, long itemId);
	public ResponseEntity<BasicResponse> updateUserImage(MultipartFile image, int userId);
	public void setItemImages(ItemResponse item);
	public ResponseEntity<BasicResponse> deleteUserImage(int userId);
	boolean isSuccessUpload(MultipartFile... image);
}