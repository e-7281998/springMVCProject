package com.shinhan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.CompanyService;
import com.shinhan.model.EmpService;
import com.shinhan.vo.EmpVO;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	EmpService eService;
	
	@Autowired
	CompanyService comService;
	
	//직원 조회
	@RequestMapping("/emplist.do")
	public String empList(Model model , HttpServletRequest request) {
		
		Map<String, ?> flashMap =
		        RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			Object message = flashMap.get("resultMessage");
			logger.info("(입력/삭제/수정에 대한 결과)message =>" + message);
		}
		
		List<EmpVO> emplist = eService.selectAll();
		logger.info(emplist.size()+"건");
		model.addAttribute("empAll", emplist);
		
		model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		return "emp/empSelect";
	}
	
	//직원 추가
	@RequestMapping(value= "/empinsert.do", method = RequestMethod.GET)
	public String registerGet(Model model) {
		model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		return "emp/empInsert";
	} 
	
	//상세보기
	@RequestMapping(value = "/empDetail.do", method = RequestMethod.GET)
	public String empDetailMethod(int empid, Model model) {
		EmpVO emp = eService.selectById(empid);
		model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		model.addAttribute("emp", emp);
		return "emp/empDetail";
	}
	
	//직원 수정
	@RequestMapping(value = "/empDetail.do", method = RequestMethod.POST)
	public String empDetailMethodPost(EmpVO emp, RedirectAttributes attr) {
		logger.info(emp.toString());
		
		String result = eService.empUpdate(emp);
		attr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/emp/emplist.do";	//다시 조회하러 가기 
	}
	
	//직원 삭제
	@RequestMapping("/empDelete.do")
	public String empDelete(int empid, RedirectAttributes attr) {
		logger.info("empid ==> " + empid);
		String result = eService.empDelete(empid);
		attr.addFlashAttribute("resultMessage", result);
		return "redirect:/emp/emplist.do";
	}
	
	//========================================================
	
	/*@RequestMapping(value= "/insert.do", method = RequestMethod.POST)
	//Map 이용 ... @RequestParam 필수! 생략불가능...
	public String registerPost(@RequestParam Map<String, String> map) {

 		for(String key:map.keySet()){
			logger.info(key);
			logger.info(map.get(key).toString());
			logger.info("===============================");
		}
		
		return "emp/empInsertResult";
	}*/
	//HTML 폼과 자바빈 객체
	//Request의 parameter를 읽어서 VO를 new해서 setter수행한다.
	@RequestMapping(value= "/empinsert.do", method = RequestMethod.POST)
	//@ModelAttribute : view에게 data를 전달
	public String registerPost (@ModelAttribute("emp") EmpVO emp,
								String address, Model model, RedirectAttributes redirectAttr,
								HttpServletRequest request, HttpSession session) {
	//public String registerPost (EmpVO emp, String address) {

		String address2 = request.getParameter("address");
		String contextPath = request.getContextPath();
		String meethod = request.getMethod();
		String remoteAddr = request.getRemoteAddr();
		
		logger.info("contextPath : " + contextPath);
		logger.info("meethod : " + meethod);
		logger.info("remoteAddr : " + remoteAddr);
		logger.info("address2 : " + address2);
		
		session.setAttribute("userInfo", "세션에 사용자 정보 저장");
		
		logger.info("emp: " +emp);
		logger.info("address: " +address);
		model.addAttribute("emp2" , emp);
		model.addAttribute("address" , address);
		
		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);
		return "redirect:/emp/emplist.do";	//다시 조회하러 가기
 	}
	/*public String registerPost(int employee_id,
							   String first_name,
							   String last_name,
							   String email,
							   String phone_number,
							   int salary,
							   int department_id,
							   int manager_id,
							   String commission_pct,
							   Date hire_date,
							   String job_id) {
		logger.info("first_name: " + first_name);
		logger.info("last_name: " + last_name);
		logger.info("email: " + email);
		logger.info("phone_number: " + phone_number);
		logger.info("salary: " + salary);
		logger.info("hire_date: " + hire_date);
		logger.info("department_id: " + department_id);
		return "emp/empInsertResult";
	}*/
	
	
	/*
	
	@RequestMapping("/three")
	public String test3(RedirectAttributes redirectAttr) {	//재요청하면서 데이터(무언가)를 전달해야 할 때
		logger.info("재요청(redirect)연습");
		redirectAttr.addFlashAttribute("resultMessage","재요청합니다.insert하세요");
		return "redirect:/emp/insert.do";
	}
	
	//다음은 재요청 연습임 
	@RequestMapping("/two")
	public String test2(@RequestParam(name = "my", required = false) String myname) {
		logger.info("재요청(redirect)연습");
		return "redirect:/emp/insert.do";
	}
	 
	//2)ModelAndView : Model + View
	@RequestMapping("/one")
	public ModelAndView test1(@RequestParam(name = "my", required = false) String myname) {
		//여기에 sample/sampleView1을 쓰거나 다다다음줄과 같이 사용하거나 
		//다다다음줄처럼 사용하려면 다음줄에서 객체 생성시 빈 값으로 생성하면 됨
		ModelAndView mv = new ModelAndView("sample/sampleView1");
		mv.addObject("subject", "modelandview return연습..."+myname);
		//mv.setViewName("sample/sampleView1");
		return mv;
	}
	
	*/
}
