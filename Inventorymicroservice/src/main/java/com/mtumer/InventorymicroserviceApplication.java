package com.mtumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventorymicroserviceApplication {
	
	
	public final static String SFG_MESSAGE_QUEUE = "sfg-message-queue";
	
	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_QUEUE);
	}

	public static void main(String[] args) {
		SpringApplication.run(InventorymicroserviceApplication.class, args);
	}

}
