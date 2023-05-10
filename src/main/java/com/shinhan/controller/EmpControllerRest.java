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
	
	//���� ��ȸ
	//produces="application/json" >> jsonŸ������ �����ڴ�. charset=utf-8: �ѱ� ������ ����
	@RequestMapping(value = "/emplist.do", 
					produces="application/json;charset=utf-8")
	public Map<String, Object> empList(Model model , HttpServletRequest request) { 
		Map<String,Object> returnMap = new HashMap<String, Object>();
  
		List<EmpVO> emplist = eService.selectByCondition(new Integer[]{0}, "", 0.0, null); 
		returnMap.put("empAll", emplist); 
		
		//������ ������ ��
		returnMap.put("deptList",comService.deptSelectAll());
		returnMap.put("jobList",comService.jobSelectAll());
		returnMap.put("managerList",comService.managerSelectAll());
		
 		return returnMap;
	}
	
	//���� �߰�
	@RequestMapping(value= "/empinsert.do",
			consumes = "application/json",
			produces = "text/plain;charset=utf-8",
			method = RequestMethod.POST)
	public String registerPost(@RequestBody EmpVO emp) {
		String result = eService.empInsert(emp);
		logger.info("�����~~~");
		return result;
	} 
	
	 
	//�󼼺���
	//@PathVariable �� path�� ������ ���� ������(empid��)�ްڴ�.
	//Jackson�� JSON���� �����Ͽ� return���� => MediaType.APPLICATION_JSON_VALUE
	//Rest���: "/empDetail.do/{empid}"
	//����)�������� ���� ���� �� json���·� ������ ��. EmpVO�� java�� �˰� �ִ� Ÿ���̱� ����.
	//����)Jackson�� ���� json���� �ٲٴ� ���� ������
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
	
	//���� ����
	//PUT : ���� , POST : �Է�
	//@RequestBody : ��û ������ body�� �����Ͱ� ���´�.
	@RequestMapping(value = "/empDetail.do",
			consumes = "application/json",
			method = RequestMethod.PUT,
			produces = "text/plain;charset=utf-8")
	public String empDetailMethodPut(@RequestBody EmpVO emp, RedirectAttributes attr) {
		logger.info(emp.toString());
		
		String result = eService.empUpdate(emp);
		attr.addFlashAttribute("resultMessage", result);
		
		return "����� "+result;	//�ٽ� ��ȸ�Ϸ� ���� 
	}
	
	//���� ����
	@DeleteMapping(value = "/empDelete.do/{empid}", produces = "text/plain;charset=utf-8")
	public String empDelete(@PathVariable int empid) {
		logger.info("empid ==> " + empid);
		String result = eService.empDelete(empid);
 		return "�����"+result;
	}
	
	//���� ��ȸ
	//@RequestMapping(value = "/empCondition.do", method = RequestMethod.GET)
	//Ajax��û�� �迭�� ���� @RequestParam("deptid[]")�� ���� ���
	//�Ϲ� ��û�̸� @RequestParam("deptid") Integer[] deptid ��, Integer[] deptid�� ����ϸ� ��
	@GetMapping("/empCondition.do")	//@GetMapping�� ���� ����. �� �����ϰ� ����� ��.
	public String selectByCondition(@RequestParam("deptid[]") Integer[] deptid, 
									String jobid, Double salary, Date hiredate, Model model) {
		logger.info(Arrays.toString(deptid) + " <<<<<< deptid");
  		List<EmpVO> emplist =  eService.selectByCondition(deptid, jobid, salary, hiredate);
		model.addAttribute("empAll", emplist);
 		return "emp/empRetrieve";
	}
	
	//========================================================
	
	/*@RequestMapping(value= "/insert.do", method = RequestMethod.POST)
	//Map �̿� ... @RequestParam �ʼ�! �����Ұ���...
	public String registerPost(@RequestParam Map<String, String> map) {

 		for(String key:map.keySet()){
			logger.info(key);
			logger.info(map.get(key).toString());
			logger.info("===============================");
		}
		
		return "emp/empInsertResult";
	}*/
	//HTML ���� �ڹٺ� ��ü
	//Request�� parameter�� �о VO�� new�ؼ� setter�����Ѵ�.
	//@RequestMapping(value= "/empinsert.do", method = RequestMethod.POST)
	//@ModelAttribute : view���� data�� ����
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
		
		session.setAttribute("userInfo", "���ǿ� ����� ���� ����");
		
		logger.info("emp: " +emp);
		logger.info("address: " +address);
		model.addAttribute("emp2" , emp);
		model.addAttribute("address" , address);
		
		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage", result);
		return "redirect:/emp/emplist.do";	//�ٽ� ��ȸ�Ϸ� ����
 	}
	 
}
