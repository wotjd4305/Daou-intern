package com.daoumarket.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.daoumarket.dao.IImageDao;
import com.daoumarket.dto.Image;
import com.daoumarket.dto.ItemResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService implements IImageService {

//    public static final String SAVE_FOLDER = "/home/intern2/tomcat_intern2/images/";
	public static final String SAVE_FOLDER = "C:/Daou_intern/backend/unithis/src/main/resources/static/images/";
	
	private final IImageDao imageDao;
	
	@Transactional
	@Override
    public int insertItemImage(MultipartFile[] images, long itemId) { // 물건 이미지 업로드
		
		String[] picture = new String[images.length];
		
		for (int i = 0; i < images.length; i++) {
    		String imageName = images[i].getOriginalFilename();
    		String imageExtension = FilenameUtils.getExtension(imageName).toLowerCase();
    		File destinationImage;
    		String destinationImageName;
    		String imageUrl = SAVE_FOLDER;
    		
    		SimpleDateFormat timeFormat = new SimpleDateFormat("yyMMddHHmmss");
    		destinationImageName = timeFormat.format(new Date()) + "_" + (i+1) + "." + imageExtension;
    		destinationImage = new File(imageUrl + destinationImageName);
    		
    		log.info("Image uploaded : {}", destinationImageName);
    		try {
    			images[i].transferTo(destinationImage);
    			picture[i] = destinationImageName;
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
	public int updateUserImage(MultipartFile image, long userId) { // 유저 이미지 업로드
    	
    	String imageName = image.getOriginalFilename();
		String imageExtension = FilenameUtils.getExtension(imageName).toLowerCase();
		File destinationImage;
		String destinationImageName;
		String imageUrl = SAVE_FOLDER;
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyMMddHHmmss");
		destinationImageName = timeFormat.format(new Date()) + "." + imageExtension;
		destinationImage = new File(imageUrl + destinationImageName);
		
		log.info("Image uploaded : {}", destinationImageName);
		try {
			image.transferTo(destinationImage);
			imageDao.updateUserImage(Image.builder().id(userId).image(destinationImageName).build());
		} catch (RuntimeException | IOException e) {
			log.error("파일 업로드에 실패했습니다.");
			return 0;
		}
		return 1;
	}
    
	@Override
    public void getItemImages(ItemResponse item) {
    	
		List<String> images = imageDao.getItemImages(item.getId());
		
		for (String filename : images) {
			item.getPicture().add(filename);
		}
    }

}
