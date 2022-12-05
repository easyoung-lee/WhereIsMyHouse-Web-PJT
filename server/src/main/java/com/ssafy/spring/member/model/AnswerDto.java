package com.ssafy.spring.member.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AnswerDto : QnA 답변", description = "회원의 질문에 대한 답변을 나타낸다")
public class AnswerDto implements Serializable {

	@ApiModelProperty(value = "답변 번호")
	private int no;
	@ApiModelProperty(value = "답변 내용")
	private String content;
	@ApiModelProperty(value = "답변자 아이디")
	private String id;
	@ApiModelProperty(value = "답변 작성일")
	private String date;
	@ApiModelProperty(value = "답변에 대한 질문 번호")
	private int qno;
	
	public AnswerDto() {}

//	public AnswerDto(int no, String content, String id, String date, int qno) {
//		this.no = no;
//		this.content = content;
//		this.id = id;
//		this.date = date;
//		this.qno = qno;
//	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnswerDto [no=").append(no).append(", content=").append(content).append(", id=").append(id)
				.append(", date=").append(date).append(", qno=").append(qno).append("]");
		return builder.toString();
	}
	
}
