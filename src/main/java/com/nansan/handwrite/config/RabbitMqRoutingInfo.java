package com.nansan.handwrite.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RabbitMqRoutingInfo {
	PERSONAL("ml.personal", "ml.personal"),
	ALL("ml.all", "ml.all");

	private final String queueName;
	private final String routingKey;
}
