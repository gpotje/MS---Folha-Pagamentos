package com.example.message;

import javax.mail.internet.MimeMessage;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Data
public class MessageListenerService {
	
	private static final Logger log = LoggerFactory.getLogger(MessageListenerService.class);
	
	private JavaMailSender javaMailSender;
	
	private String from;
	private String subject;
	
	public MessageListenerService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void receiveMessage(final FolhaMessage folhaMessage) {
		
		log.info("receiving message : {}", folhaMessage.toString());
		
		try {
			sendMail(folhaMessage);
		} catch (Exception e) {
			log.error("Error sending email to :{}", folhaMessage.getEmail());
			e.printStackTrace();
		}
		
	}
	
	public void sendMail(FolhaMessage folhaMessage) throws Exception {
		
		MimeMessage message =  javaMailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);
		
		mimeMessageHelper.setTo(folhaMessage.getEmail());
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(folhaMessage.getFuncionarios());
	
		javaMailSender.send(message);
	}

}
