package com.ssafy.spring.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.spring.member.model.AnswerDto;
import com.ssafy.spring.member.model.PageBean;
import com.ssafy.spring.member.model.QuestionDto;
import com.ssafy.spring.member.model.mapper.AnswerDao;
import com.ssafy.spring.member.model.mapper.QuestionDao;
import com.ssafy.spring.util.PageUtility;

@Service
public class QnaServiceImp implements QnaService {
	
	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public void answerInsert(AnswerDto answer) {
		answerDao.answerInsert(answer);
	}

	@Override
	public void answerUpdate(AnswerDto answer) {
		answerDao.answerUpdate(answer);
	}

	@Override
	public void answerDelete(int no) {
		answerDao.answerDelete(no);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AnswerDto> answerSelectAll(int qno) {
		return answerDao.answerSelectAll(qno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> questionSearchMy(String id, PageBean bean) {
		int cnt = questionDao.totalCountMy(id, bean);
		try {
			PageUtility page = new PageUtility(bean.getInterval()
											, cnt
											, bean.getPageNo()
											, "");
			bean.setPageLink(page.getPageBar());
		} catch (Exception e) {
			
		}
		return questionDao.questionSearchMy(id, bean);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuestionDto> questionSearchAll(PageBean bean) {
		int cnt = questionDao.totalCountAll(bean);
		try {
			PageUtility page = new PageUtility(bean.getInterval()
											, cnt
											, bean.getPageNo()
											, "");
			bean.setPageLink(page.getPageBar());
		} catch (Exception e) {
			
		}
		return questionDao.questionSearchAll(bean);
	}

	@Override
	@Transactional(readOnly = true)
	public QuestionDto questionSelect(int no) {
		return questionDao.questionSelect(no);
	}

	@Override
	public void questionInsert(QuestionDto question) {
		questionDao.questionInsert(question);

	}

	@Override
	public void questionUpdate(QuestionDto question) {
		questionDao.questionUpdate(question);
	}

	@Override
	public void questionDelete(int no) {
		questionDao.questionDelete(no);
	}

}
