package com.daoumarket.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dao.IImageDao;
import com.daoumarket.dao.IUserDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Image;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.User;
import com.daoumarket.jwt.IJWTService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService implements IImageService {

//    public static final String SAVE_FOLDER = "/home/intern2/tomcat_intern2/images/";
	public static final String SAVE_FOLDER = "C:/Daou_intern/backend/daoumarket/src/main/resources/static/images/";
	private final IImageDao imageDao;
	private final IUserDao userDao;
	private final IJWTService jwtService;
	
	private static final HashSet<String> imageExtention = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif", "svg", "bmp"));
	
	@Transactional
	@Override
    public int insertItemImage(MultipartFile[] images, long itemId) { // 물건 이미지 업로드
		
		if(!isSuccessUpload(images)) {
			return 0;
		}
		
		String[] picture = new String[images.length];
		
		for (int i = 0; i < images.length; i++) {
    		String imageName = images[i].getOriginalFilename();
    		String imageExtension = FilenameUtils.getExtension(imageName).toLowerCase();
    		File destinationImage;
    		String newImageName;
    		String imageUrl = SAVE_FOLDER;
    		
    		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    		newImageName = UUID.randomUUID().toString() + "-" + timeFormat.format(new Date()) + "." + imageExtension;
    		
    		destinationImage = new File(imageUrl + newImageName);
    		
    		log.info("Image uploaded : {}", newImageName);
    		try {
    			images[i].transferTo(destinationImage);
    			picture[i] = newImageName;
    		} catch (RuntimeException | IOException e) {
    			log.error("파일 업로드에 실패했습니다.");
    			return 0;
    		}
		}
		
		int result = imageDao.insertItemImage(Image.builder().id(itemId).picture(picture).build());
		
		if(result == 0)
			return 0;

        return 1;
    }
    
	@Transactional
    @Override
	public ResponseEntity<BasicResponse> updateUserImage(MultipartFile image, int userId) { // 유저 이미지 업로드
		
		BasicResponse response = new BasicResponse();
		
		if(!isSuccessUpload(image)) {
			log.error("파일 확장자가 이미지가 아니거나, 업로드 할 수 없는 크기입니다.");
			response.message = "파일 확장자가 이미지가 아니거나, 업로드 할 수 없는 크기입니다.";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
    	
    	String imageName = image.getOriginalFilename();
		String imageExtension = FilenameUtils.getExtension(imageName).toLowerCase();
		File destinationImage;
		String newImageName;
		String imageUrl = SAVE_FOLDER;
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		newImageName = UUID.randomUUID().toString() + "-" + timeFormat.format(new Date()) + "." + imageExtension;
		
		destinationImage = new File(imageUrl + newImageName);
		
		log.info("Image uploaded : {}", newImageName);
		try {
			image.transferTo(destinationImage);
			imageDao.updateUserImage(Image.builder().id(userId).image(newImageName).build());
			User user = userDao.getUserByUserId(userId);
			String token = jwtService.makeJwt(user);
			response.data = token;
			
		} catch (Exception e) {
			log.error("파일 업로드에 실패했습니다.");
			response.message = "업로드 실패";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		response.message = "업로드 성공";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
	@Override
    public void setItemImages(ItemResponse item) {
    	
		List<String> images = imageDao.getItemImages(item.getItemId());
		
		item.setPicture(images);
    }

	@Override
	public ResponseEntity<BasicResponse> deleteUserImage(int userId) {
		
		BasicResponse response = new BasicResponse();
		
		int result = imageDao.deleteUserImage(userId);
		
		if(result == 0) {
			response.message = "삭제 실패";
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		User user = userDao.getUserByUserId(userId);
		String token;
		try {
			token = jwtService.makeJwt(user);
			response.data = token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.message = "삭제 성공";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public boolean isSuccessUpload(MultipartFile... image) {
		
		for (int i = 0; i < image.length; i++) {
			String nowExtension = FilenameUtils.getExtension(image[i].getOriginalFilename()).toLowerCase();
			
			if(!imageExtention.contains(nowExtension))
				return false;
			
			String fileName = image[i].getOriginalFilename();
			
			if(fileName.contains("%00") || fileName.contains("0x00"))
				return false;
			
			if(image[i].getSize() == 0 || image[i].getSize() > 5242880)
				return false;
		}
		
		return true;
	}
}
