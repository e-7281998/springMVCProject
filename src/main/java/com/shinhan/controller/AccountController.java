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
	
	//produces = "text/html;charset=UTF-8" 을 통해 응답문서에 한글깨지지 않도록
	@GetMapping(value = "/transfer.do", produces = "text/html;charset=UTF-8")
	@ResponseBody	//응답문서의 바디로 가고 싶을경우
	//응답문서 받을 때 ... response.getWriter().append("aaa").와  같음 => aaa를 만들어서 응답문서에 넣어라
	public String transactionTet() {
		service.transfer();
		return "<h1>transfer transactionTest 입니당</h1>";
	}
}
