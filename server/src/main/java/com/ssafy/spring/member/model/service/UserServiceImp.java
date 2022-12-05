package com.ssafy.spring.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spring.member.model.UserDto;
import com.ssafy.spring.member.model.mapper.UserDao;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void userRegister(UserDto user) {
		userDao.userRegister(user);
	}
	
	@Override
	public void userUpdate(UserDto user) {
		userDao.userUpdate(user);
	}

	@Override
	public void userDelete(String id) {
		userDao.userDelete(id);
	}

	@Override
	public UserDto login(UserDto user) throws SQLException {
		if (user.getId()== null || user.getPass() == null)
			return null;
		return sqlSession.getMapper(UserDao.class).login(user);
	}

	@Override
	public UserDto userInfo(String id) throws SQLException {
		return sqlSession.getMapper(UserDao.class).userInfo(id);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		System.out.println("refreshToken이 뭘까!!!!!! " + refreshToken);
		sqlSession.getMapper(UserDao.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws SQLException {
		return sqlSession.getMapper(UserDao.class).getRefreshToken(id);
	}

	@Override
	public void deleteRefreshToken(String id) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		sqlSession.getMapper(UserDao.class).deleteRefreshToken(map);
	}

	/*@Override
	public UserDto select(UserDto user) {
		return dao.select(user);
	}

	@Override
	public void insert(UserDto user) {
		dao.insert(user);
	}

	@Override
	public UserDto search(String id) {
		return dao.search(id);
	}

	@Override
	public void update(UserDto user) {
		dao.update(user);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}*/
}
