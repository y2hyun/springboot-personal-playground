package com.y2hyun.playground.domain.enums;

public class BoardEnums {
	
	public enum BoardType {

		NOTICE("お知らせ"),
		FREE("雑談");
		
		private String value;
		
		BoardType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
}
