package com.ssafy.spring.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.spring.member.model.UserDto;

public interface UserService {

	/* UserDto select(UserDto user); // 아이디와 비밀번호로 찾기
	void insert(UserDto user); // 회원가입
	UserDto search(String id); // 아이디로 찾기
	void update(UserDto user); // 회원 정보 업데이트
	void delete(String id); // 회원 정보 삭제 */
	
	public void userRegister(UserDto user);		// 회원 정보 등록
	public void userUpdate(UserDto user);		// 회원 정보 수정
	public void userDelete(String id);			// 회원 정보 삭제

	public UserDto login(UserDto user) throws SQLException;
	public UserDto userInfo(String id) throws SQLException;
	public void saveRefreshToken(String id, String refreshToken) throws SQLException;
	public Object getRefreshToken(String id) throws SQLException;
	public void deleteRefreshToken(String id) throws SQLException;
	
}
