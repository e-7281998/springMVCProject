package net.shinhan.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect //Advisor + Target
public class StopWatchAdvice {

	//within : 어떤 클래스에 적용하고 싶은지.. 주 업무를 호출할 순 없음.
	//@Pointcut("within(com.shinhan.model.*)")	//모든 파일 다 
	@Pointcut("within(com.shinhan.model.EmpDAOMybatis)")
	public void targetMethod() { }
	
	@Before("targetMethod()")
	public void before(JoinPoint jp) {
		System.out.println("------------StopWatchAdvice @Before------------");
		System.out.println(jp.getSignature().getName());
	}
	
	@After("targetMethod()")
	public void after(JoinPoint jp) {
		System.out.println("------------StopWatchAdvice @After------------");
		System.out.println(jp.getSignature().getName());
	}
	
	@AfterThrowing("targetMethod()")
	public void afterThrowing(JoinPoint jp) {
		System.out.println("------------StopWatchAdvice @AfterThrowing------------");
		System.out.println(jp.getSignature().getName());
	}
	
	@AfterReturning("targetMethod()")
	public void afterReturning(JoinPoint jp) {
		System.out.println("------------StopWatchAdvice @AfterReturning------------");
		System.out.println(jp.getSignature().getName());
	}
 	
	@Around("targetMethod()")
	public Object invoke(ProceedingJoinPoint jp) throws Throwable {
		// 보조업무
		System.out.println("StopWatchAdvice [메서드 호출 전 ] : " + jp.getSignature().getName() );
		StopWatch watch = new StopWatch("계산시간");
		watch.start();
		
		// 주업무를 수행한다.
		Object object = jp.proceed();
		
		// 보조업무
		System.out.println("*****" + jp.getSignature().getName() + "메서드 호출 후");
		watch.stop();
		
		System.out.println("주업무 수행 시간:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		
		return object;
	}
}
