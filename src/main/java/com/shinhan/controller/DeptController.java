package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.model.DeptService;
import com.shinhan.vo.DeptVO;

@Controller
@RequestMapping("/dept")
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService service;
	
	//에러페이지 어노테이션으로 처리하기 : Exception의 하위 클래스는 전부 여기로 옴
	//Local error 처리  
	/*@ExceptionHandler(Exception.class)
	public String errorProcess(Exception ex) {
		ex.printStackTrace();
		logger.info(ex.getMessage());
		return "/error/error500";
	}*/
	
	//1. 부서조회
	@RequestMapping("/selectAll.do")
	public String deptRetrieve(Model model) {
		logger.info("---------1. 부서 조회 ---------"); 
		model.addAttribute("deptList", service.deptSelectAll());
		return "dept/deptlist";
	}
	
	//2. 부서입력
	@RequestMapping(value = "/insert.do", method=RequestMethod.GET)
	public String deptInsert(DeptVO dept, RedirectAttributes attr) {
		logger.info("---------2. 부서 입력 ---------");   
		return "dept/deptinsert";
	}
	//2. 부서입력 요청
	@RequestMapping(value = "/insert.do", method=RequestMethod.POST)
	public String deptInsertPost(DeptVO dept, RedirectAttributes attr) {
		logger.info("---------2. 부서 입력(post) ---------"); 
		int result = service.deptInsert(dept);
 		
		return "redirect:/dept/selectAll.do";
	}
	//3. 부서정보 상세보기
	@RequestMapping(value = "/update.do", method=RequestMethod.GET)
	public String deptUpdate(int deptid, Model model) {
		logger.info("---------3. 부서정보 상세보기 ---------");  
		model.addAttribute("dept", service.deptByID(deptid));
		return "dept/deptupdate";
	}
	
	//3. 부서정보 상세보기..수정 
	@RequestMapping(value = "/update.do", method=RequestMethod.POST)
	public String deptUpdatePost(DeptVO dept,  RedirectAttributes attr) {
		logger.info("---------3. 부서정보 상세보기... 수정(post) ---------"); 
		logger.info(dept.toString());
		int result = service.deptUpdate(dept);
		attr.addFlashAttribute("result", result+"건 수정");
		
		return "redirect:/dept/selectAll.do";
	}
	
	//4. 부서삭제
	@RequestMapping(value = "/delete.do")
	public String deptDelete(int deptid,  RedirectAttributes attr) {
		logger.info("---------4. 부서 삭제 ---------"); 
		int result = service.deptDelete(deptid);
		attr.addFlashAttribute("result", result+"건 삭제");
		
		return "redirect:/dept/selectAll.do";
	}
}
