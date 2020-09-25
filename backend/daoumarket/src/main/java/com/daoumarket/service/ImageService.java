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
    public int insertItemImage(MultipartFile[] images, long id) { // 물건 이미지 업로드
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
    			imageDao.insertItemImage(Image.builder().id(id).picture(destinationImageName).build());
    		} catch (RuntimeException | IOException e) {
    			log.error("파일 업로드에 실패했습니다.");
    			return 0;
    		}
		}

        return 1;
    }
    
	@Transactional
    @Override
	public int insertUserImage(MultipartFile image, long id) { // 유저 이미지 업로드
    	
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
			imageDao.insertUserImage(Image.builder().id(id).picture(destinationImageName).build());
		} catch (RuntimeException | IOException e) {
			log.error("파일 업로드에 실패했습니다.");
			return 0;
		}
		return 1;
	}
    
	@Override
    public List<Image> getImage(long id) {
    	return imageDao.getImage(id);
    }

}
