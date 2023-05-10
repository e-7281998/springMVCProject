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
	@ResponseBody	//응답문서의 바디로 가고 싶을경우
	//응답문서 받을 때 ... responst.getWriter().append().와  같음
	public String transactionTet() {
		service.transfer();
		return "transfer transactionTest";
	}
}
