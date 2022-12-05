package com.ssafy.spring.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.spring.house.model.HouseInfo;
import com.ssafy.spring.house.model.mapper.HouseInfoDao;

@Service
public class HouseInfoServiceImp implements HouseInfoService {

	@Autowired
	private HouseInfoDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<HouseInfo> searchAll() {
		// TODO Auto-generated method stub
		return dao.searchAll();
	}

	@Override
	@Transactional(readOnly = true)
	public HouseInfo search(int no) {
		return dao.search(no);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HouseInfo> searchEach(int pgno, String sido, String gugun, String dong, String aptName) {
		return dao.searchEach(pgno, sido, gugun, dong, aptName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HouseInfo> searchBookMark(String dongCode) {
		return dao.searchBookMark(dongCode);
	}
}
