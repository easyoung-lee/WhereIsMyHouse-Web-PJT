package com.ssafy.spring.bookmark.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spring.bookmark.model.service.BookMarkService;
import com.ssafy.spring.house.model.HouseInfo;
import com.ssafy.spring.house.model.HouseInfoException;
import com.ssafy.spring.house.model.service.HouseInfoService;
import com.ssafy.spring.member.model.UserDto;
import com.ssafy.spring.util.ParameterCheck;

import io.swagger.annotations.ApiOperation;

//@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class BookMarkRestController {

	private Logger logger = LoggerFactory.getLogger(BookMarkRestController.class);

	@Autowired
	private BookMarkService service;

	@Autowired
	private HouseInfoService houseInfoService;

	private static final String SUCCESS="success";
	
//	@GetMapping("/setBookmarkForm")
//	public String setBookmarkForm() {
//		return "/bookmark/setbookmark";
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

//	@GetMapping("/bookmark")
//	public String setBookmark(HttpServletRequest request, Model model) {
//		String dongCode = "";
//		try {
//			dongCode = request.getParameter("dong");
//		} catch (Exception e) {
//		}
//		HttpSession session = request.getSession();
//
//		UserDto user = (UserDto) session.getAttribute("userInfo");
//		if (dongCode != "") {
//			service.insert(user.getId(), dongCode);
//		}
////		request.setAttribute("msg", "나만의 관심지역이 설정되었습니다");		
////		Map<String, String> dongMap = service.search(user.getId());
//		List<Map<String, Object>> dongMap = service.search(user.getId());
////		Map.Entry<String, Object> entry = dongMap.entrySet().iterator().next();
////		String key = entry.getKey();
////		Object value = entry.getValue();
////		logger.debug("--------------------->>>>>"+key+" "+value);
//		model.addAttribute("dongMap", dongMap);
//		session.setAttribute("dongMap", dongMap);
//
//		return "/bookmark/bookmarkList";
//	}
	
	@ApiOperation(value="Bookmark set", notes = "Bookmark 정보를 조건에 맞게 설정")
	@GetMapping("/bookmark")
	public ResponseEntity<?> setBookmark(String id, String dong){
		String dongCode = dong;
		logger.debug("BookmarkRestController.setBookmark................id:{}", id, " dongCode:{}", dongCode);
		
		service.insert(id, dongCode);
		
		List<Map<String, Object>> dongMap = service.search(id);
		logger.debug("BookmarkRestController.setBookmark................dongMap:{}", dongMap);
		
		if(dongMap!=null && !dongMap.isEmpty()) {
			return new ResponseEntity<List<Map<String, Object>>>(dongMap, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="Bookmark search", notes = "내가 등록한 Bookmark 주소 search")
	@GetMapping("/bookmark/{id}")
	public ResponseEntity<?> searchBookmark(@PathVariable String id){
		logger.debug("BookmarkRestController.searchBookmark................id:{}", id);
		List<Map<String, Object>> dongMap = service.search(id);
		logger.debug("BookmarkRestController.searchBookmark................dongMap:{}", dongMap);
		
		if(dongMap!=null && !dongMap.isEmpty()) {
			return new ResponseEntity<List<Map<String, Object>>>(dongMap, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="Bookmark AptData", notes = "현재 Bookmark 조건에 맞게 아파트 리스트 가져오기")
	@GetMapping("/bookmark/search/{dongCode}")
	public ResponseEntity<?> bookmarkMapList(@PathVariable String dongCode){
		logger.debug("BookmarkRestController.bookmarkMapList................dongCode:{}", dongCode);		

		List<HouseInfo> houseInfoList = houseInfoService.searchBookMark(dongCode);
		
		logger.debug("BookmarkRestController.bookmarkMapList................houseInfoList:{}", houseInfoList);
		
		if(houseInfoList!=null && !houseInfoList.isEmpty()) {
			return new ResponseEntity<List<HouseInfo>>(houseInfoList, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="bookmark 삭제", notes = "id, dongCode에 해당하는 bookmark를 삭제")
	@DeleteMapping("/bookmark/{id}/{dongCode}")
	public ResponseEntity<String> deleteBookmark(@PathVariable String id, @PathVariable String dongCode) {
		logger.debug("BookmarkRestController.deleteBookmark................id:{}", id, " dongCode:{}", dongCode);
		service.remove(id, dongCode);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
