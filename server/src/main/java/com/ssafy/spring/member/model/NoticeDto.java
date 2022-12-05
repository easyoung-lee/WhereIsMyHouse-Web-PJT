package com.ssafy.spring.member.model;

import java.io.Serializable;

public class NoticeDto implements Serializable {
	
	private int no;
	private String title;
	private String content;
	private String id;
	private String date;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoticeDto [no=").append(no).append(", title=").append(title).append(", content=")
				.append(content).append(", id=").append(id).append(", date=").append(date).append("]");
		return builder.toString();
	}

}
