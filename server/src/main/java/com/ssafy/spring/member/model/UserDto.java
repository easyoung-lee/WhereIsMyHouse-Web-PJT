package com.ssafy.spring.member.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto implements Serializable {
	
	@ApiModelProperty(value = "회원 아이디")
	private String id;
	@ApiModelProperty(value = "회원 이름")
	private String name;
	@ApiModelProperty(value = "회원 비밀번호")
	private String pass;
	@ApiModelProperty(value = "회원 전화번호")
	private String phone;
	@ApiModelProperty(value = "회원 Token")
	private String token;
	
	public UserDto() {
		super();
	}


//	public User(String id, String name, String pass, String phone) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.pass = pass;
//		this.phone = phone;
//	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", pass=" + pass + ", phone=" + phone + ", token=" + token
				+ "]";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	
}
