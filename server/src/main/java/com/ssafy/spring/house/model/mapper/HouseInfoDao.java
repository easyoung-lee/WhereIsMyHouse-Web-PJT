package com.ssafy.spring.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.spring.house.model.HouseInfo;

@Mapper
public interface HouseInfoDao {

	HouseInfo search(int no);

	List<HouseInfo> searchBookMark(String dongCode);
	
	List<HouseInfo> searchEach(int pgno, String sido, String gugun, String dong, String aptName);
	
	List<HouseInfo> searchAll();
}
