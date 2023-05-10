package com.shinhan.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shinhan.model.CompanyService;
import com.shinhan.model.EmpService;
import com.shinhan.vo.EmpVO;

@RestController //@Controller + @ResponseBody
@RequestMapping("/restemp")
public class EmpControllerRest {
	
	Logger logger = LoggerFactory.getLogger(EmpControllerRest.class);
	
	@Autowired
	EmpService eService;
	
	@Autowired
	CompanyService comService;
	
	//직원 조회
	//produces="application/json" >> json타입으로 보내겠다. charset=utf-8: 한글 깨지지 말고
	@RequestMapping(value = "/emplist.do", 
					produces="application/json;charset=utf-8")
	public Map<String, Object> empList(Model model , HttpServletRequest request) { 
		Map<String,Object> returnMap = new HashMap<String, Object>();
  
		List<EmpVO> emplist = eService.selectByCondition(new Integer[]{0}, "", 0.0, null); 
		returnMap.put("empAll", emplist); 
		
		//다음은 지워도 됨
		returnMap.put("deptList",comService.deptSelectAll());
		returnMap.put("jobList",comService.jobSelectAll());
		returnMap.put("managerList",comService.managerSelectAll());
		
 		return returnMap;
	}
	
	//직원 추가
	@RequestMapping(value= "/empinsert.do",
			consumes = "application/json",
			produces = "text/plain;charset=utf-8",
			method = RequestMethod.POST)
	public String registerPost(@RequestBody EmpVO emp) {
		String result = eService.empInsert(emp);
		logger.info("여기옴~~~");
		return result;
	} 
	
	 
	//상세보기
	//@PathVariable 은 path에 들어오는 것을 값으로(empid로)받겠다.
	//Jackson이 JSON으로 변경하여 return해줌 => MediaType.APPLICATION_JSON_VALUE
	//Rest방식: "/empDetail.do/{empid}"
	//내가)브라우저로 값을 보낼 때 json형태로 보내야 함. EmpVO는 java가 알고 있는 타입이기 때문.
	//내가)Jackson이 값을 json으로 바꾸는 것을 도와줌
	@RequestMapping(
					value = "/empDetail.do/{empid}",
					produces = MediaType.APPLICATION_JSON_VALUE,
					method = RequestMethod.GET)
	public EmpVO empDetailMethod(@PathVariable int empid, Model model) {
		EmpVO emp = eService.selectById(empid);
		
		/*model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		model.addAttribute("emp", emp);*/
		return emp;
	}
	
	//직원 수정
	//PUT : 수정 , POST : 입력
	//@RequestBody : 요청 문서의 body에 데이터가 들어온다.
	@RequestMapping(value = "/empDetail.do",
			consumes = "application/json",
			method = RequestMethod.PUT,
			produces = "text/plain;charset=utf-8")
	public String empDetailMethodPut(@RequestBody EmpVO emp, RedirectAttributes attr) {
		logger.info(emp.toString());
		
		String result = eService.empUpdate(emp);
		attr.addFlashAttribute("resultMessage", result);
		
		return "결과는 "+result;	//다시 조회하러 가기 
	}
	
	//직원 삭제
	@DeleteMapping(value = "/empDelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String empDelete(@PathVariable int empid) {
		logger.info("empid ==> " + empid);
		String result = eService.empDelete(empid);
 		return "결과는"+result;
	}
	
	//조건 조회
	//@RequestMapping(value = "/empCondition.do", method = RequestMethod.GET)
	//Ajax요청시 배열이 오면 @RequestParam("deptid[]")와 같이 사용
	//일반 요청이면 @RequestParam("deptid") Integer[] deptid 즉, Integer[] deptid만 사용하면 됨
	@GetMapping("/empCondition.do")	//@GetMapping은 위와 같음. 더 간단하게 사용한 것.
	public String selectByCondition(@RequestParam("deptid[]") Integer[] deptid, 
									String jobid, Double salary, Date hiredate, Model model) {
		logger.info(Arrays.toString(deptid) + " <<<<<< deptid");
  		List<EmpVO> emplist =  eService.selectByCondition(deptid, jobid, salary, hiredate);
		model.addAttribute("empAll", emplist);
 		return "emp/empRetrieve";
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
	//@RequestMapping(value= "/empinsert.do", method = RequestMethod.POST)
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
	 
}
