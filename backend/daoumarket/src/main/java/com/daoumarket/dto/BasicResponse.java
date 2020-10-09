package com.daoumarket.dto;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse {
	@ApiModelProperty(value = "isSuccess", position = 1)
	public boolean isSuccess;
	@ApiModelProperty(value = "message", position = 2)
	public String message;
	@ApiModelProperty(value = "data", position = 3)
	public Object data;
	@ApiModelProperty(value = "pageMaker", position = 4)
	public PageMaker pageMaker;
}