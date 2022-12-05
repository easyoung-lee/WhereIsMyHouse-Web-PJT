package com.ssafy.spring.member.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spring.member.model.AnswerDto;
import com.ssafy.spring.member.model.PageBean;
import com.ssafy.spring.member.model.QuestionDto;
import com.ssafy.spring.member.model.service.QnaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class QnaRestController {

	private Logger logger = LoggerFactory.getLogger(QnaRestController.class);
	private static final String SUCCESS="success";
	
	@Autowired
	private QnaService qnaService;
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.error("ErrorHandler.............................");
		logger.error("ErrorMessage.............................{}",e.getMessage());
		e.printStackTrace();
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		if (e instanceof Exception) {
			return new ResponseEntity<String>(e.getMessage(), resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}else {
			return new ResponseEntity<String>("처리 중 오류 발생", resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	/** -----------------질문-----------------  **/
	@ApiOperation(value="전체 질문 검색", notes = "bean에 해당하는 질문 정보를 검색")
	@GetMapping("/question")
	public ResponseEntity<?> searchAllQuestion(PageBean bean){
		logger.debug("QnaRestController.searchAllQuestion............................bean{}", bean);
		List<QuestionDto> questionAllList = qnaService.questionSearchAll(bean);
		logger.debug("QnaRestController.searchAllQuestion............................questions{}", questionAllList);
		if(questionAllList != null && !questionAllList.isEmpty()) {
			return new ResponseEntity<List<QuestionDto>>(questionAllList, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
//	@ApiOperation(value="내 질문 검색", notes = "bean, id에 해당하는 질문 정보를 검색")
//	@GetMapping("/question/search")
//	public ResponseEntity<?> searchMyQuestion(String id, PageBean bean){
//		logger.debug("QnaRestController.searchAllQuestion............................bean{}", bean);
//		List<QuestionDto> questionMyList = qnaService.questionSearchMy(id, bean);
//		logger.debug("QnaRestController.searchAllQuestion............................questions{}", questionMyList);
//		if(questionMyList != null && !questionMyList.isEmpty()) {
//			return new ResponseEntity<List<QuestionDto>>(questionMyList, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
	
	@ApiOperation(value="질문 상세정보 검색", notes = "no에 해당하는 질문 상세정보를 검색")
	@GetMapping("/question/{no}")
	public ResponseEntity<?> selectQuestion(@PathVariable int no){
		logger.debug("QnaRestController.selectQuestion............................no:{}", no);
		QuestionDto question = qnaService.questionSelect(no);
		logger.debug("QnaRestController.selectQuestion............................question:{}", question);
		if(question != null) {
			return new ResponseEntity<QuestionDto>(question, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="질문 작성", notes = "전달받은 데이터를 통해서 질문 작성")
	@PostMapping("/question")
	public ResponseEntity<String> insertQuestion(@RequestBody QuestionDto question) {
		logger.debug("QnaRestController insertQuestion..............................{}",question);
		qnaService.questionInsert(question);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="질문 수정", notes = "전달 받은 데이타를 이용해서 질문 작성")
	@PutMapping("/question")
	public ResponseEntity<String> udpateQuestion(@RequestBody QuestionDto question) {
		logger.debug("QnaRestController.udpateQuestion....................question:{}", question);
		qnaService.questionUpdate(question);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="질문 삭제", notes = "no에 해당하는 질문을 삭제")
	@DeleteMapping("/question/{no}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int no) {
		logger.debug("QnaRestController.deleteQuestion....................no:{}", no);
		qnaService.questionDelete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	
	/** -----------------답변-----------------  **/
	@ApiOperation(value="답변 작성", notes = "전달받은 데이터를 통해서 답변 작성")
	@PostMapping("/answer")
	public ResponseEntity<String> insertAnswer(@RequestBody AnswerDto answer) {
		logger.debug("QnaRestController regist..............................{}",answer);
		qnaService.answerInsert(answer);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="답변 수정", notes = "전달 받은 데이타를 이용해서 답변 작성")
	@PutMapping("/answer")
	public ResponseEntity<String> udpateAnswer(@RequestBody AnswerDto answer) {
		logger.debug("QnaRestController.udpate....................no:{}", answer.getNo());
		qnaService.answerUpdate(answer);
		logger.debug("QnaRestController.udpate....................answer:{}", answer);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="답변 삭제", notes = "no에 해당하는 답변을 삭제")
	@DeleteMapping("/answer/{no}")
	public  ResponseEntity<String> deleteAnswer(@PathVariable int no) {
		logger.debug("QnaRestController.delete....................no:{}", no);
		qnaService.answerDelete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="답변 검색", notes = "no에 해당하는 답변을 검색")
	@GetMapping("/answer/{qno}")
	public ResponseEntity<?> searchAnswer(@PathVariable int qno) {
//		int qno1 = Integer.parseInt(qno);
		logger.debug("QnaRestController.search....................qno:{}", qno);
		List<AnswerDto> answers = qnaService.answerSelectAll(qno);
//		logger.debug("BookController.search....................book:{}", answers);
		if(answers != null) {
			return new ResponseEntity<List<AnswerDto>>(answers, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
}
