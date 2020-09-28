package com.daoumarket.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.ItemResponse;

public interface IImageService {
	public int insertItemImage(MultipartFile[] images, long id);
	public ResponseEntity<BasicResponse> updateUserImage(MultipartFile image, long id);
	public void getItemImages(ItemResponse item);
	public ResponseEntity<BasicResponse> deleteUserImage(long id);
}