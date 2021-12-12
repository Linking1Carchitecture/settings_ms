package com.linking.settingsms.RabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer implements MessageListener {

	public void onMessage(Message message) {
		System.out.println("Consuming Message - " + new String(message.getBody()));
	}
}