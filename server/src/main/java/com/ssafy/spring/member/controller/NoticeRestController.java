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
import com.ssafy.spring.member.model.NoticeDto;
import com.ssafy.spring.member.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class NoticeRestController {

	private Logger logger = LoggerFactory.getLogger(NoticeRestController.class);
	private static final String SUCCESS="success";
	
	@Autowired
	private NoticeService noticeService;
	
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

	@ApiOperation(value="공지사항 작성", notes = "전달받은 데이터를 통해서 공지사항 작성")
	@PostMapping("/notice")
	public ResponseEntity<String> NoticeInsert(@RequestBody NoticeDto notice) {
		logger.debug("NoticeRestCotroller regist..............................{}",notice);		
		noticeService.NoticeInsert(notice);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="공지사항 수정", notes = "전달 받은 데이타를 이용해서 공지사항 작성")
	@PutMapping("/notice")
	public ResponseEntity<String> NoticeUpdate(@RequestBody NoticeDto notice) {
//		logger.debug("NoticeRestCotroller.udpate....................no:{}", notice.getNo());
		logger.debug("NoticeRestCotroller.udpate....................notice:{}", notice);
		noticeService.NoticeUpdate(notice);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="공지사항 삭제", notes = "no에 해당하는 공지사항을 삭제")
	@DeleteMapping("/notice/{no}")
	public  ResponseEntity<String> NoticeDelete(@PathVariable int no) {
		logger.debug("NoticeRestCotroller.delete....................no:{}", no);
		noticeService.NoticeDelete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="공지사항 검색", notes = "no에 해당하는 공지사항을 검색")
	@GetMapping("/notice/{no}")
	public ResponseEntity<?> NoticeSelect(@PathVariable int no) {
		logger.debug("NoticeRestCotroller.search....................no:{}", no);
		NoticeDto notice = noticeService.NoticeSelect(no);
		if(notice != null) {
			return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="공지사항 검색", notes = "공지사항 목록을 검색")
	@GetMapping("/notice")
	public ResponseEntity<?> NoticeSearch() {
		logger.debug("NoticeRestCotroller.search....................");
		List<NoticeDto> notices = noticeService.NoticeSearch();
//		logger.debug("BookController.search....................book:{}", answers);
		if(notices != null) {
			return new ResponseEntity<List<NoticeDto>>(notices, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
