package com.linking.settingsms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit
@SpringBootApplication
public class SettingsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingsMsApplication.class, args);
	}

}
