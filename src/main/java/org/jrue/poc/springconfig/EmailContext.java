package org.jrue.poc.springconfig;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class EmailContext {

	private String host;
	private int port;
	private String username;
	private String password;
	private String smtpAuth;
	private String tlsEnable;
	private String transPortProtocol;
	
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(host);
		javaMailSenderImpl.setPort(port);
		javaMailSenderImpl.setUsername(username);
		javaMailSenderImpl.setPassword(password);
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", smtpAuth); 
		javaMailProperties.put("mail.smtp.starttls.enable", tlsEnable);
		javaMailProperties.put("mail.transport.protocol", transPortProtocol);
		javaMailSenderImpl.setJavaMailProperties(javaMailProperties);
		return javaMailSenderImpl;
	}

	@Bean
	public MimeMessage mailMessage(JavaMailSender mailSender) {
		return mailSender.createMimeMessage();
	}
	
	@Bean
	public MimeMessageHelper mailMessageHelper(MimeMessage mailMessage) {
		return new MimeMessageHelper(mailMessage);
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getTlsEnable() {
		return tlsEnable;
	}

	public void setTlsEnable(String tlsEnable) {
		this.tlsEnable = tlsEnable;
	}

	public String getTransPortProtocol() {
		return transPortProtocol;
	}

	public void setTransPortProtocol(String transPortProtocol) {
		this.transPortProtocol = transPortProtocol;
	}
}