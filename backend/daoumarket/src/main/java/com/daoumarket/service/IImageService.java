package com.daoumarket.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dto.Image;

public interface IImageService {
	public int insertItemImage(MultipartFile[] images, long id);
	public int insertUserImage(MultipartFile image, long id);
	public List<Image> getImage(long id);
}