package com.hyphen.service;

import java.util.List;

import com.hyphen.exception.ChatException;
import com.hyphen.exception.UserException;
import com.hyphen.model.Chat;
import com.hyphen.model.User;
import com.hyphen.request.GroupChatRequest;

public interface ChatService {

	public Chat createChat(User reqUser, Long userId2) throws UserException;
	
	public Chat findChatById(Long chatId) throws ChatException;
	
	public List<Chat> findAllChatByUserId(Long userId) throws UserException;
	
	public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException;
	
	public Chat addUserToGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException;
	
	public Chat renameGroup(Long chatId, String groupName, User reqUser) throws UserException, ChatException;
	
	public Chat removeFromGroup(Long chatId, Long userId, User reqUser) throws UserException, ChatException;
	
	public void deleteChat(Long chatId, Long userId) throws UserException, ChatException;
}
