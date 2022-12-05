package com.ssafy.spring.house.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spring.house.model.HouseInfo;
import com.ssafy.spring.house.model.HouseInfoException;
import com.ssafy.spring.house.model.service.HouseInfoService;
import com.ssafy.spring.util.ParameterCheck;

import io.swagger.annotations.ApiOperation;

//@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class HouseInfoRestController {

	private Logger logger = LoggerFactory.getLogger(HouseInfoRestController.class);
	
	@Autowired
	private HouseInfoService service;

	private static final String SUCCESS="success";
	
//	@GetMapping("/houseInfoForm")
//	public String houseInfoForm(){
//		return "/house/list";
//	}
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.error("ErrorHandler.............................");
		logger.error("ErrorMessage.............................{}",e.getMessage());
		e.printStackTrace();
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		if (e instanceof HouseInfoException) {
			return new ResponseEntity<String>(e.getMessage(), resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}else {
			return new ResponseEntity<String>("처리 중 오류 발생", resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
//	@ApiOperation(value="HouseList 목록 정보", notes = "House 정보를 검색 조건에 맞게 검색한 결과")
//	@GetMapping("/house/{pgno}/{sido}/{gugun}/{dong}/{aptName}")
//	public ResponseEntity<?> houseInfoList( @PathVariable("pgno") String pgno, @PathVariable("sido") String sido, @PathVariable("gugun") String gugun,
//			@PathVariable("dong") String dong, @PathVariable("aptName") String aptName){
//		int pgno1 = (ParameterCheck.notNumberToOne(pgno) -1)*15;
//		sido = ParameterCheck.nullToBlank(sido);
//		gugun = ParameterCheck.nullToBlank(gugun);
//		dong = ParameterCheck.nullToBlank(dong);
//		aptName = ParameterCheck.nullToBlank(aptName);
////		System.out.println(pgno);
////		System.out.println(sido + gugun + dong + aptName);
//		logger.debug("HouseInfoRestController.houseInfoList................pgno:{}", pgno, " sido:{}", sido, " gugun:{}", gugun, " dong:{}", dong, " aptName:{}", aptName);
//		List<HouseInfo> houseInfoList = service.searchEach(pgno1, sido, gugun, dong, aptName);
//		if(houseInfoList!=null && !houseInfoList.isEmpty()) {
//			return new ResponseEntity<List<HouseInfo>>(houseInfoList, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
	@ApiOperation(value="HouseList 목록 정보", notes = "House 정보를 검색 조건에 맞게 검색한 결과")
	@GetMapping("/house")
	public ResponseEntity<?> houseInfoList(String pgno, String sido, String gugun, String dong, String aptName){
		int pgno1 = (ParameterCheck.notNumberToOne(pgno) -1)*15;
		sido = ParameterCheck.nullToBlank(sido);
		gugun = ParameterCheck.nullToBlank(gugun);
		dong = ParameterCheck.nullToBlank(dong);
		aptName = ParameterCheck.nullToBlank(aptName);
//		System.out.println(pgno);
//		System.out.println(sido + gugun + dong + aptName);
		logger.debug("HouseInfoRestController.houseInfoList................pgno:{}", pgno, " sido:{}", sido, " gugun:{}", gugun, " dong:{}", dong, " aptName:{}", aptName);
		List<HouseInfo> houseInfoList = service.searchEach(pgno1, sido, gugun, dong, aptName);
		if(houseInfoList!=null && !houseInfoList.isEmpty()) {
			return new ResponseEntity<List<HouseInfo>>(houseInfoList, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="House 정보 검색", notes = "no에 해당하는 House 정보를 검색")
	@GetMapping("/house/{no}")
	public ResponseEntity<?> houseInfoSearch(@PathVariable("no") int no){
//		int no = Integer.parseInt(request.getParameter("no"));
		logger.debug("HouseInfoRestController.houseInfoSearch............................no:{}", no);
		HouseInfo houseInfo = service.search(no);
		logger.debug("HouseInfoRestController.houseInfoSearch............................houseInfo:{}", houseInfo);
		if(houseInfo != null) {
			return new ResponseEntity<HouseInfo>(houseInfo, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
