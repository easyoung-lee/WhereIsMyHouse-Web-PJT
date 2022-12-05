package com.ssafy.spring.bookmark.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.spring.bookmark.model.mapper.BookMarkDao;

@Service
public class BookMarkServiceImp implements BookMarkService {

	@Autowired
	private BookMarkDao dao;
	
	@Override
	@Transactional
	public void insert(String user_id, String dongCode) {
		dao.insert(user_id, dongCode);
	}

	@Override
	@Transactional
	public void remove(String user_id, String dongCode) {
		dao.remove(user_id, dongCode);
	}

	@Override
	public List<Map<String, Object>> search(String user_id) {
		return dao.search(user_id);
	}
}
