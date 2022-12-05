package com.ssafy.spring.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.spring.member.model.AnswerDto;

@Mapper
public interface AnswerDao {

	void answerInsert(AnswerDto answer);				// 답변 작성
	void answerUpdate(AnswerDto answer);				// 답변 수정
	void answerDelete(int no);							// 답변 삭제
	List<AnswerDto> answerSelectAll(int qno);			// 질문에 해당하는 답변 찾기
}