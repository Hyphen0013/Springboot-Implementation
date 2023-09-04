package com.hyphen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.hyphen.model.Message;

@Controller
public class RealTimeChat {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public RealTimeChat(SimpMessagingTemplate simpMessagingTemplate) {
	    this.simpMessagingTemplate = simpMessagingTemplate;
	}
	
//	@MessageMapping("/message")
//	@SendTo("/group/public")
//	public Message reciveMessage(@Payload Message message) {
//		
//		simpMessagingTemplate.convertAndSend("/group/"+message.getChat().getId().toString(), message);
//		return message;
//	}
	
	@MessageMapping("/message")
	@SendTo("/group/public")
	public Message reciveMessage(@Payload Message message) {
	    if (message.getChat() == null) {
	        System.out.println("Chat is null for message with ID: " + message.getId());
	        simpMessagingTemplate.convertAndSend("/group/"+message.getChat().getId().toString(), message);
	    } else {
	        System.out.println("Chat is available for message with ID: " + message.getId());
	        simpMessagingTemplate.convertAndSend("/group/"+message.getChat().getId().toString(), message);
	    }
		return message;
	    
	    // rest of your method
	}	
}
