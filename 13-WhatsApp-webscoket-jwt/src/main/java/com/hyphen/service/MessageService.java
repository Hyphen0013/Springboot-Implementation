package com.hyphen.service;

import java.util.List;

import com.hyphen.exception.ChatException;
import com.hyphen.exception.MessageException;
import com.hyphen.exception.UserException;
import com.hyphen.model.Message;
import com.hyphen.model.User;
import com.hyphen.request.SendMessageRequest;

public interface MessageService {
	
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
	
	public List<Message> getChatsMessages(Long chatId, User reqUser) throws ChatException, UserException;
	
	public Message findMessageById(Long messageId) throws MessageException;
	
	public void deleteMessage(Long messageId, User reqUser) throws MessageException, UserException;
}
