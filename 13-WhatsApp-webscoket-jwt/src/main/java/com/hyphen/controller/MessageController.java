package com.hyphen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyphen.exception.ChatException;
import com.hyphen.exception.MessageException;
import com.hyphen.exception.UserException;
import com.hyphen.model.Message;
import com.hyphen.model.User;
import com.hyphen.request.SendMessageRequest;
import com.hyphen.response.ApiResponse;
import com.hyphen.service.ChatService;
import com.hyphen.service.MessageService;
import com.hyphen.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/create")
	public ResponseEntity<Message> sendMessageHandler(
			@RequestBody SendMessageRequest sendMessageRequest,
			@RequestHeader("Authorization") String token			
	) throws UserException, ChatException {
		
		User reqUser = userService.findUserProfile(token);
		
		sendMessageRequest.setUserId(reqUser.getId());
		Message message = messageService.sendMessage(sendMessageRequest);
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@GetMapping("/chat/{chatId}")
	public ResponseEntity<List<Message>> getChatMessagesHandler(
			@PathVariable Long chatId,
			@RequestHeader("Authorization") String token			
	) throws UserException, ChatException {
		
		User reqUser = userService.findUserProfile(token);
		
		List<Message> messages = messageService.getChatsMessages(chatId, reqUser);
		
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{messageId}")
	public ResponseEntity<ApiResponse> deleteMessageHandler(
			@PathVariable Long messageId,
			@RequestHeader("Authorization") String token			
	) throws UserException, MessageException {
		
		User reqUser = userService.findUserProfile(token);
		
		messageService.deleteMessage(messageId, reqUser);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Message deleted successfully!");
		res.setStatus(true);		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
}
