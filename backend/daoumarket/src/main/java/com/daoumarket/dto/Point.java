package com.daoumarket.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Service
public class Point {
	private long pointId;
	private LocalDateTime date;
	private int score;
	private int userId;
}
