package com.daoumarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Image {

	private long id;
	private String image;
	private String[] picture;
	
	@Builder
	public Image(long id, String image, String[] picture) {
		this.id = id;
		this.image = image;
		this.picture = picture;
	}
}
