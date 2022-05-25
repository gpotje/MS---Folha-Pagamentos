package com.example.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.domain.dto.FolhaMessage;

@Service
public class FolhaMessageSender {
	
	public static final String EXCHANGE_NAME = "folhapagamento.exchange";
	public static final String ROUTING_KEY = "envio.email";

	private static final Logger log =  LoggerFactory.getLogger(FolhaMessageSender.class);
	
	private final RabbitTemplate rabbitTemplate;
	
	public FolhaMessageSender(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void enviaEmailDaFolha(String email, String nomes) {
		final var messagem = new FolhaMessage(email,nomes);
		
		log.info("Enviando mensagem...");
		
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY,messagem);
	
	}
	
}
