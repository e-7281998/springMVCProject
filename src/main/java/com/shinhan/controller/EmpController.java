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
	
	//���� ��ȸ
	@RequestMapping("/emplist.do")
	public String empList(Model model , HttpServletRequest request) {
		
		Map<String, ?> flashMap =
		        RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			Object message = flashMap.get("resultMessage");
			logger.info("(�Է�/����/������ ���� ���)message =>" + message);
		}
		
		List<EmpVO> emplist = eService.selectAll();
		logger.info(emplist.size()+"��");
		model.addAttribute("empAll", emplist);
		
		model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		return "emp/empSelect";
	}
	
	//���� �߰�
	@RequestMapping(value= "/empinsert.do", method = RequestMethod.GET)
	public String registerGet(Model model) {
		model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		return "emp/empInsert";
	} 
	
	//�󼼺���
	@RequestMapping(value = "/empDetail.do", method = RequestMethod.GET)
	public String empDetailMethod(int empid, Model model) {
		EmpVO emp = eService.selectById(empid);
		model.addAttribute("deptList",comService.deptSelectAll());
		model.addAttribute("jobList",comService.jobSelectAll());
		model.addAttribute("managerList",comService.managerSelectAll());
		
		model.addAttribute("emp", emp);
		return "emp/empDetail";
	}
	
	//���� ����
	@RequestMapping(value = "/empDetail.do", method = RequestMethod.POST)
	public String empDetailMethodPost(EmpVO emp, RedirectAttributes attr) {
		logger.info(emp.toString());
		
		String result = eService.empUpdate(emp);
		attr.addFlashAttribute("resultMessage", result);
		
		return "redirect:/emp/emplist.do";	//�ٽ� ��ȸ�Ϸ� ���� 
	}
	
	//���� ����
	@RequestMapping("/empDelete.do")
	public String empDelete(int empid, RedirectAttributes attr) {
		logger.info("empid ==> " + empid);
		String result = eService.empDelete(empid);
		attr.addFlashAttribute("resultMessage", result);
		return "redirect:/emp/emplist.do";
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
	@RequestMapping(value= "/empinsert.do", method = RequestMethod.POST)
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
	public String test3(RedirectAttributes redirectAttr) {	//���û�ϸ鼭 ������(����)�� �����ؾ� �� ��
		logger.info("���û(redirect)����");
		redirectAttr.addFlashAttribute("resultMessage","���û�մϴ�.insert�ϼ���");
		return "redirect:/emp/insert.do";
	}
	
	//������ ���û ������ 
	@RequestMapping("/two")
	public String test2(@RequestParam(name = "my", required = false) String myname) {
		logger.info("���û(redirect)����");
		return "redirect:/emp/insert.do";
	}
	 
	//2)ModelAndView : Model + View
	@RequestMapping("/one")
	public ModelAndView test1(@RequestParam(name = "my", required = false) String myname) {
		//���⿡ sample/sampleView1�� ���ų� �ٴٴ����ٰ� ���� ����ϰų� 
		//�ٴٴ�����ó�� ����Ϸ��� �����ٿ��� ��ü ������ �� ������ �����ϸ� ��
		ModelAndView mv = new ModelAndView("sample/sampleView1");
		mv.addObject("subject", "modelandview return����..."+myname);
		//mv.setViewName("sample/sampleView1");
		return mv;
	}
	
	*/
}
