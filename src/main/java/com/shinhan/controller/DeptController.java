package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	//1. �μ���ȸ
	@RequestMapping("/selectAll.do")
	public String deptRetrieve(Model model) {
		logger.info("---------1. �μ� ��ȸ ---------"); 
		model.addAttribute("deptList", service.deptSelectAll());
		return "dept/deptlist";
	}
	
	//2. �μ��Է�
	@RequestMapping(value = "/insert.do", method=RequestMethod.GET)
	public String deptInsert(DeptVO dept, RedirectAttributes attr) {
		logger.info("---------2. �μ� �Է� ---------");  
 		int result = service.deptInsert(dept);
		attr.addFlashAttribute("result", result+"�� �Է�");
		
		return "dept/deptinsert";
	}
	
	@RequestMapping(value = "/insert.do", method=RequestMethod.POST)
	public String deptInsertPost(DeptVO dept, RedirectAttributes attr) {
		logger.info("---------2. �μ� �Է�(post) ---------"); 
		int result = service.deptInsert(dept);
 		
		return "redirect:/dept/selectAll.do";
	}
	//3. �μ����� �󼼺���
	@RequestMapping(value = "/update.do", method=RequestMethod.GET)
	public String deptUpdate(int deptid, Model model) {
		logger.info("---------3. �μ����� �󼼺��� ---------");  
		model.addAttribute("dept", service.deptByID(deptid));
		return "dept/deptupdate";
	}
	
	//3. �μ����� �󼼺���..���� 
	@RequestMapping(value = "/update.do", method=RequestMethod.POST)
	public String deptUpdatePost(DeptVO dept,  RedirectAttributes attr) {
		logger.info("---------3. �μ����� �󼼺���... ����(post) ---------"); 
		logger.info(dept.toString());
		int result = service.deptUpdate(dept);
		attr.addFlashAttribute("result", result+"�� ����");
		
		return "redirect:/dept/selectAll.do";
	}
	
	//4. �μ�����
	@RequestMapping(value = "/delete.do")
	public String deptDelete(int deptid,  RedirectAttributes attr) {
		logger.info("---------4. �μ� ���� ---------"); 
		int result = service.deptDelete(deptid);
		attr.addFlashAttribute("result", result+"�� ����");
		
		return "redirect:/dept/selectAll.do";
	}
}
