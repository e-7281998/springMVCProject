package net.shinhan.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	
 	@Pointcut("execution(* com.shinhan.model.*.select*(..))")
	public void cut1() {} 
	
	@Around("cut1()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("-- cut1 주 업무 시작 전 (calss 이름) "+ jp.getTarget().getClass().getName());
		Object obj = jp.proceed();
		System.out.println("-- cut1 주 업무 시작 후 (함수 이름) "+ jp.getSignature().getName());
		return obj;
	}
	
}
