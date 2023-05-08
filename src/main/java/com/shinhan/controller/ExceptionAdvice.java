package com.shinhan.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

//��� Controller�� Exception �߻��� ����ȴ�.
@ControllerAdvice
public class ExceptionAdvice {
	
	Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	//Exceptino�� ������ ��� �� �޼��带 �����Ѵ�.
	@ExceptionHandler(Exception.class)
	public String processException(Exception ex) {
		//exception Ŭ���� �̸��� ���´�.
		logger.info(ex.getClass().getName());
		logger.info(ex.getMessage());
		return "/error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)	//��� �ص��ǰ� ���ص� ����
	public ModelAndView handlerError404(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "404����... �������� �������� ����");
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error/error404");
		return mv;
	
	}
	
}
