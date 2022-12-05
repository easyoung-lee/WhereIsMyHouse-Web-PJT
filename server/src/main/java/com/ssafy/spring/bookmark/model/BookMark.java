package com.ssafy.spring.bookmark.model;

public class BookMark {
	private String user_id;
	private String dongCong;
	
	public BookMark() {}

	public BookMark(String user_id, String dongCong) {
		super();
		this.user_id = user_id;
		this.dongCong = dongCong;
	}

	@Override
	public String toString() {
		return "BookMark [user_id=" + user_id + ", dongCong=" + dongCong + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDongCong() {
		return dongCong;
	}

	public void setDongCong(String dongCong) {
		this.dongCong = dongCong;
	}
	
}
