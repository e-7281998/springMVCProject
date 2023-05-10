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
	
	//produces = "text/html;charset=UTF-8" �� ���� ���乮���� �ѱ۱����� �ʵ���
	@GetMapping(value = "/transfer.do", produces = "text/html;charset=UTF-8")
	@ResponseBody	//���乮���� �ٵ�� ���� �������
	//���乮�� ���� �� ... response.getWriter().append("aaa").��  ���� => aaa�� ���� ���乮���� �־��
	public String transactionTet() {
		service.transfer();
		return "<h1>transfer transactionTest �Դϴ�</h1>";
	}
}
