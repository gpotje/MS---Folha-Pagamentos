package com.example.config;

import java.util.Properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailSenderConfig {
	
	private String host;
	private int port;
	
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender =  new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		
	
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		
		javaMailSender.setJavaMailProperties(properties);
		
		return javaMailSender();
	}

}
