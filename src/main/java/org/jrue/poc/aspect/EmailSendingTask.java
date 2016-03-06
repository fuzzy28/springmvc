package org.jrue.poc.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.jrue.poc.springmybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class EmailSendingTask {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	MimeMessage mimeMessage;

	@Autowired
	MimeMessageHelper mimeMessageHelper;
	
	@Async
	public void doSend(User user) throws Exception {
		Map<String, Object> mapModel = new HashMap<>();
		mapModel.put("name", user.getName());
		mapModel.put("receipient", mimeMessageHelper.getMimeMessage().getAllRecipients()[0].toString());
		String mailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mailmessage.vtl", "UTF-8",
				mapModel);
		mimeMessageHelper.setText(mailText, true);
		mailSender.send(mimeMessage);
	}
}
