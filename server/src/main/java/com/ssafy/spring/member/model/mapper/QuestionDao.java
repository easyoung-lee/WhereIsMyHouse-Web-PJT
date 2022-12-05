package com.ssafy.spring.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.spring.member.model.PageBean;
import com.ssafy.spring.member.model.QuestionDto;

@Mapper
public interface QuestionDao {

	List<QuestionDto> questionSearchMy(String id, PageBean bean); // 전체검색

	List<QuestionDto> questionSearchAll(PageBean bean); // MY검색

	int totalCountAll(PageBean bean);
	
	int totalCountMy(String id, PageBean bean);

	QuestionDto questionSelect(int no); // 상세정보

	void questionInsert(QuestionDto question); // 입력

	void questionUpdate(QuestionDto question); // 수정

	void questionDelete(int no); // 삭제

}
