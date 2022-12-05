package com.ssafy.spring.member.model.service;

import java.util.List;

import com.ssafy.spring.member.model.AnswerDto;
import com.ssafy.spring.member.model.PageBean;
import com.ssafy.spring.member.model.QuestionDto;

public interface QnaService {

	/** 답변 작성 **/
	void answerInsert(AnswerDto answer); // 답변 작성

	void answerUpdate(AnswerDto answer); // 답변 수정

	void answerDelete(int no); // 답변 삭제

	List<AnswerDto> answerSelectAll(int qno); // 질문에 해당하는 답변 찾기

	/** 질문 작성 **/
	List<QuestionDto> questionSearchMy(String id, PageBean bean); // 전체검색

	List<QuestionDto> questionSearchAll(PageBean bean); // MY검색

	QuestionDto questionSelect(int no); // 상세정보

	void questionInsert(QuestionDto question); // 입력

	void questionUpdate(QuestionDto question); // 수정

	void questionDelete(int no); // 삭제
}
