package com.ssafy.spring.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.spring.member.model.NoticeDto;


@Mapper
public interface NoticeDao {
	
	List<NoticeDto> NoticeSearch();	// 목록

	NoticeDto NoticeSelect(int no); // 상세정보

	void NoticeInsert(NoticeDto notice); // 입력

	void NoticeUpdate(NoticeDto notice); // 수정

	void NoticeDelete(int no); // 삭제

}
