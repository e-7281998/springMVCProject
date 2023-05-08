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

//모든 Controller에 Exception 발생시 실행된다.
@ControllerAdvice
public class ExceptionAdvice {
	
	Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	//Exceptino의 하위는 모두 이 메서드를 수행한다.
	@ExceptionHandler(Exception.class)
	public String processException(Exception ex) {
		//exception 클래스 이름이 나온다.
		logger.info(ex.getClass().getName());
		logger.info(ex.getMessage());
		return "/error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)	//얘는 해도되고 안해도 무방
	public ModelAndView handlerError404(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "404오류... 페이지가 존재하지 않음");
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error/error404");
		return mv;
	
	}
	
}
