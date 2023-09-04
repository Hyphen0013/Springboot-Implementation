package com.hyphen.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyphen.exception.ChatException;
import com.hyphen.exception.UserException;
import com.hyphen.model.Chat;
import com.hyphen.model.User;
import com.hyphen.request.GroupChatRequest;
import com.hyphen.request.SingleChatRequest;
import com.hyphen.response.ApiResponse;
import com.hyphen.service.ChatService;
import com.hyphen.service.UserService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;	
	
	public ChatController(
		UserService userService,
		ChatService chatService
	) {
		this.userService = userService;
		this.chatService = chatService;
	}
	
	@PostMapping("/single")
	public ResponseEntity<Chat> createChatHandler(
			@RequestBody SingleChatRequest singleChatRequest,
			@RequestHeader("Authorization") String token
	) throws UserException {
		User reqUser = userService.findUserProfile(token);
		
		Chat chat = chatService.createChat(reqUser, singleChatRequest.getUserIds());
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}
	
	
	@PostMapping("/group")
	public ResponseEntity<Chat> createGroupChatHandler(
			@RequestBody GroupChatRequest groupChatRequest,
			@RequestHeader("Authorization") String token
	) throws UserException {
		User reqUser = userService.findUserProfile(token);
		Chat chat = chatService.createGroup(groupChatRequest, reqUser);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}
	
	
	@GetMapping("/{chatId}")
	public ResponseEntity<Chat> findChatByHandler(
			@PathVariable Long chatId,
			@RequestHeader("Authorization") String token
	) throws ChatException {
		
		Chat chat = chatService.findChatById(chatId);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<List<Chat>> findAllChatByUserIdHandler(
			@RequestHeader("Authorization") String token
	) throws ChatException, UserException {
		User reqUser = userService.findUserProfile(token);
		
		List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
		
		return new ResponseEntity<>(chats, HttpStatus.OK);
	}
	
	@PutMapping("/{chatId}/add/{userId}")
	public ResponseEntity<Chat> addUserToGroupHandler(
			@PathVariable Long chatId,
			@PathVariable Long userId,
			@RequestHeader("Authorization") String token
	) throws ChatException, UserException {
		User reqUser = userService.findUserProfile(token);
		
		Chat chat = chatService.addUserToGroup(userId, chatId, reqUser);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}	
	
	@PutMapping("/{chatId}/remove/{userId}")
	public ResponseEntity<Chat> removeUserFromGroupHandler(
			@PathVariable Long chatId,
			@PathVariable Long userId,
			@RequestHeader("Authorization") String token
	) throws ChatException, UserException {
		User reqUser = userService.findUserProfile(token);
		
		Chat chat = chatService.removeFromGroup(chatId, userId, reqUser);
		return new ResponseEntity<Chat>(chat, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{chatId}")
	public ResponseEntity<ApiResponse> deleteChatGroupHandler(
			@PathVariable Long chatId,
			@RequestHeader("Authorization") String token
	) throws ChatException, UserException {
		User reqUser = userService.findUserProfile(token);
		
		chatService.deleteChat(chatId, reqUser.getId());
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Chat deleted successfully!");
		res.setStatus(true);		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
}
