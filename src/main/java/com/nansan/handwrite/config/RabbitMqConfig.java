package com.nansan.handwrite.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String EXCHANGE_NAME = "handwrite.exchange";

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue personalQueue() {
		return new Queue(RabbitMqRoutingInfo.PERSONAL.getQueueName());
	}

	@Bean
	public Queue allQueue() {
		return new Queue(RabbitMqRoutingInfo.ALL.getQueueName());
	}

	@Bean
	public Binding personalBinding(Queue personalQueue, TopicExchange exchange) {
		return BindingBuilder.bind(personalQueue).to(exchange).with(RabbitMqRoutingInfo.PERSONAL.getRoutingKey());
	}

	@Bean
	public Binding allBinding(Queue allQueue, TopicExchange exchange) {
		return BindingBuilder.bind(allQueue).to(exchange).with(RabbitMqRoutingInfo.ALL.getRoutingKey());
	}
}
