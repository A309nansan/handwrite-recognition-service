package com.nansan.handwrite.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitMqPublisher {

	private final RabbitTemplate rabbitTemplate;

	public void sendToPersonal(String message) {
		rabbitTemplate.convertAndSend(
			RabbitMqConfig.EXCHANGE_NAME,
			RabbitMqRoutingInfo.PERSONAL.getRoutingKey(),
			message
		);
	}

	public void sendToAll(String message) {
		rabbitTemplate.convertAndSend(
			RabbitMqConfig.EXCHANGE_NAME,
			RabbitMqRoutingInfo.ALL.getRoutingKey(),
			message
		);
	}
}
