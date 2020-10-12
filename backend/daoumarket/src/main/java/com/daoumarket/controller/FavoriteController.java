package com.daoumarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Favorite;
import com.daoumarket.dto.FavoriteDeleteReq;
import com.daoumarket.service.IFavoriteService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class FavoriteController {
	private final IFavoriteService favoriteService;
	
	@PostMapping("/favorite")
	@ApiOperation("item에 favorite 설정")
	public ResponseEntity<BasicResponse> insertFavorite(@RequestBody Favorite favorite){
		log.info("FavoriteController : insertFavorite");
		
		return favoriteService.insertFavorite(favorite);
	}
	
	@DeleteMapping("/favorite/{itemId}/{userId}")
	@ApiOperation("favorite id를 이용해서 키워드 삭제")
	public ResponseEntity<BasicResponse> deleteFavorite(@PathVariable int userId,@PathVariable long itemId) {
		log.info("FavoriteController : deleteFavorite => {}", userId,itemId);
		FavoriteDeleteReq temp = new FavoriteDeleteReq(userId, itemId);
		return favoriteService.deleteFavorite(temp);
	}
	
	@GetMapping("/favorite/{userId}")
	@ApiOperation("유저 id를 이용해서 favorite 이력 확인")
	public ResponseEntity<BasicResponse> getFavoriteList(@PathVariable int userId){
		log.info("FavoriteController : getFavoriteList => {}", userId);
		
		return favoriteService.getFavoriteList(userId);
	}
}
