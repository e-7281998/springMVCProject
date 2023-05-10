package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service	//@Component + service
@Transactional(propagation = Propagation.REQUIRED)  //propagationd이란 tracsaction 전파규칙 설정
public class AccountService {
	
	@Autowired
	AccountDAOMybatis dao;
	
	//이체 서비스
	public void transfer() {
		dao.update1();	//OK
		dao.update2();	//FAIL
	}
}
