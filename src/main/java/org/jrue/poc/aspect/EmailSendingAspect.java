package org.jrue.poc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jrue.poc.springmybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class EmailSendingAspect {
	
	@Autowired
	EmailSendingTask emaiSendingTask;
	
	@Around("execution(* *.registerNewUser(..))")
	public String onCreatingNewUserAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object returnVal = proceedingJoinPoint.proceed();
		User user = (User) proceedingJoinPoint.getArgs()[0];
		emaiSendingTask.doSend(user);
		return returnVal.toString();
	}

}