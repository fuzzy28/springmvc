package org.jrue.poc.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jrue.poc.springmybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Aspect
@Configuration
public class EmailSendingAspect {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	MimeMessage mimeMessage;

	@Autowired
	MimeMessageHelper mimeMessageHelper;
	
	@Around("execution(* *.registerNewUser(..))")
	public String onCreatingNewUserAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object returnVal = proceedingJoinPoint.proceed();
		User user = (User) proceedingJoinPoint.getArgs()[0];
		Map<String, Object> mapModel = new HashMap<>();
		mapModel.put("name", user.getName());
		mapModel.put("receipient", mimeMessageHelper.getMimeMessage().getAllRecipients()[0].toString());
		String mailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mailmessage.vtl",
				"UTF-8", mapModel);
		mimeMessageHelper.setText(mailText, true);
		mailSender.send(mimeMessage);
		return returnVal.toString();
	}
}