package com.shinhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.model.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	@GetMapping("/transfer.do")
	@ResponseBody	//���乮���� �ٵ�� ���� �������
	//���乮�� ���� �� ... responst.getWriter().append().��  ����
	public String transactionTet() {
		service.transfer();
		return "transfer transactionTest";
	}
}
