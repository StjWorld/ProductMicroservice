package com.mtumer.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mtumer.InventorymicroserviceApplication;
import com.mtumer.entity.Product;

@Service
public class RabbitSender {
	
	private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public RabbitSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	
	
	public void sendProductMessage(String name, Integer qty) {
		String pName = name.toString();
		String quantity = qty.toString();

		
		Map<String,String> actionmap = new HashMap<>();

		actionmap.put("name", pName);
		actionmap.put("quantity", quantity);
		
		log.info("Sending message for test purpose");
		rabbitTemplate.convertAndSend(InventorymicroserviceApplication.SFG_MESSAGE_QUEUE, actionmap);
		
	}
	
	public void sendUpdateMessage(Long id,String name, Integer qty) {
		String PId= id.toString();
		String quantity = qty.toString();
		
		Map<String,String> secondMap = new HashMap<>();
		secondMap.put("id", PId);
		secondMap.put("name", name);
		secondMap.put("quantity", quantity);
		
		log.info("Sending message for test purpose");
		rabbitTemplate.convertAndSend(InventorymicroserviceApplication.SFG_MESSAGE_QUEUE, secondMap);
		
	}
		
	}
	
	
	
