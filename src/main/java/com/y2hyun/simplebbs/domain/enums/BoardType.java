package com.y2hyun.simplebbs.domain.enums;

public enum BoardType {

	notice("お知らせ"),
	free("雑談");
	
	private String value;
	
	BoardType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
