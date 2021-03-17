package com.spring.enums;

public enum ApiErrorEnum {
	NOT_FOUND_ID("Not found ID"),
	NOT_FOUND_ENUM("Not found enum");
	
	ApiErrorEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	private String text;
}
