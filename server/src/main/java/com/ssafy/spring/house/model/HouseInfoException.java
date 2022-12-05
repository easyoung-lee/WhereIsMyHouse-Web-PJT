package com.ssafy.spring.house.model;

public class HouseInfoException extends RuntimeException {
	public HouseInfoException() {
		super("House 정보를 처리 중 오류 발생");
	}
	public HouseInfoException(String msg) {
		super(msg);
	}
}
