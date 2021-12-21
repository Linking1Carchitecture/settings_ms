package com.linking.settingsms.RabbitMQ;

import com.linking.settingsms.Model.Configuration;
import com.linking.settingsms.Repositories.ConfigurationRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer{

    @Autowired
    ConfigurationRepository configurationRepository;

	@RabbitListener(queues = {"${spring.rabbitmq.queue}"})
	public void consume(@Payload String email) {
		// System.out.println("Consuming Message - " + email);
		Configuration config = new Configuration(email);
		// System.out.println("Configuration created - " + config.toString());
		configurationRepository.save(config);
		// System.out.println("Saved - " + saved.toString());
	}
}