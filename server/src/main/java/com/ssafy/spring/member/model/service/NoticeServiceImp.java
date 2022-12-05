package com.ssafy.spring.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spring.member.model.NoticeDto;
import com.ssafy.spring.member.model.mapper.NoticeDao;

@Service
public class NoticeServiceImp implements NoticeService {
	
	@Autowired
	private NoticeDao dao;

	@Override
	public NoticeDto NoticeSelect(int no) {
		return dao.NoticeSelect(no);
	}

	@Override
	public void NoticeInsert(NoticeDto notice) {
		dao.NoticeInsert(notice);
	}

	@Override
	public void NoticeUpdate(NoticeDto notice) {
		dao.NoticeUpdate(notice);
	}

	@Override
	public void NoticeDelete(int no) {
		dao.NoticeDelete(no);
	}

	@Override
	public List<NoticeDto> NoticeSearch() {
		return dao.NoticeSearch();
	}

}
