package com.ssafy.spring.bookmark.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMarkDao {
	
	void insert(String user_id, String dongCode);
	
	void remove(String user_id, String dongCode);
	
	List<Map<String, Object>> search(String user_id);
	
}
