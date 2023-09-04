package com.hyphen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyphen.exception.ChatException;
import com.hyphen.exception.MessageException;
import com.hyphen.exception.UserException;
import com.hyphen.model.Chat;
import com.hyphen.model.Message;
import com.hyphen.model.User;
import com.hyphen.repository.MessageRepository;
import com.hyphen.request.SendMessageRequest;

@Service
public class MessageServiceImplements implements MessageService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	public MessageServiceImplements(
			UserService userService,
			MessageRepository messageRepository,
			ChatService chatService
	) {
		this.userService = userService;
		this.messageRepository = messageRepository;
		this.chatService = chatService;
	}

	@Override
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
		User user = userService.findUserById(req.getUserId());
		Chat chat = chatService.findChatById(req.getChatId());
		
		Message message = new Message();
		message.setChat(chat);
		message.setUser(user);
		message.setContent(req.getContent());
		message.setTimestamp(LocalDateTime.now());
		
		messageRepository.save(message);
		return message;
	}

	@Override
	public List<Message> getChatsMessages(Long chatId, User reqUser) throws ChatException, UserException {
		Chat chat = chatService.findChatById(chatId);
		
		if(!chat.getUsers().contains(reqUser)) {
			throw new UserException("You are not related to this chat " + chat.getId());
		}
		
		List<Message> messages = messageRepository.findByChatId(chat.getId());
		
		return messages;
	}

	@Override
	public Message findMessageById(Long messageId) throws MessageException {
		Optional<Message> opt = messageRepository.findById(messageId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new MessageException("Message not found with id: " + messageId);
	}

	@Override
	public void deleteMessage(Long messageId, User reqUser) throws MessageException, UserException {
		Message message = findMessageById(messageId);
		
		if(message.getUser().getId().equals(reqUser.getId())) {
			messageRepository.deleteById(messageId);
		}
		
		throw new UserException("You can't delete another user's message! " + reqUser.getFull_name());
	}

}
