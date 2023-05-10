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

	//within : � Ŭ������ �����ϰ� ������.. �� ������ ȣ���� �� ����.
	//@Pointcut("within(com.shinhan.model.*)")	//��� ���� �� 
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
		// ��������
		System.out.println("StopWatchAdvice [�޼��� ȣ�� �� ] : " + jp.getSignature().getName() );
		StopWatch watch = new StopWatch("���ð�");
		watch.start();
		
		// �־����� �����Ѵ�.
		Object object = jp.proceed();
		
		// ��������
		System.out.println("*****" + jp.getSignature().getName() + "�޼��� ȣ�� ��");
		watch.stop();
		
		System.out.println("�־��� ���� �ð�:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		
		return object;
	}
}
