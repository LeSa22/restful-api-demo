package com.spring.enums;

public enum StatusWorkEnum {

	PLANING("Planing"),
	DOING("Doing"),
	COMPLETE("Complete");
	
	StatusWorkEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	private String text;
}
