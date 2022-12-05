package com.ssafy.spring.bookmark.model.service;

import java.util.List;
import java.util.Map;

public interface BookMarkService {
	
	void insert(String user_id, String dongCode);
	
	void remove(String user_id, String dongCode);
	
	List<Map<String, Object>> search(String user_id);

}
