package com.hyphen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyphen.exception.ChatException;
import com.hyphen.exception.UserException;
import com.hyphen.model.Chat;
import com.hyphen.model.User;
import com.hyphen.repository.ChatRepository;
import com.hyphen.request.GroupChatRequest;

@Service
public class ChatServiceImplementation implements ChatService {
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserService userService;

	public ChatServiceImplementation(
		ChatRepository chatRepository,
		UserService userService
	) {
		this.chatRepository = chatRepository;
		this.userService = userService;
	}
	
	@Override
	public Chat createChat(User reqUser, Long userId2) throws UserException {
		User user = userService.findUserById(userId2);
		
		Chat isChatExist = chatRepository.findSingleChatByUserIds(user, reqUser);
		
		if(isChatExist != null) {
			return isChatExist;
		}
		
		Chat chat = new Chat();
		chat.setCreatedBy(reqUser);
		chat.getUsers().add(user);
		chat.getUsers().add(reqUser);
		chat.setGroup(false);
		chatRepository.save(chat);
		
		return chat;
	}

	@Override
	public Chat findChatById(Long chatId) throws ChatException {
		
		Optional<Chat> chat = chatRepository.findById(chatId);
		
		if(chat.isPresent()) {
			return chat.get();
		}
		throw new ChatException("Chat not found with id: " + chatId);
	}

	@Override
	public List<Chat> findAllChatByUserId(Long userId) throws UserException {
		User user = userService.findUserById(userId);
		
		List<Chat> chats = chatRepository.findChatByUserId(user.getId());
		
		return chats;
	}

	@Override
	public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
		
	    if (req == null || reqUser == null) {
	        throw new UserException("Request or requesting user is null.");
	    }
	    
		Chat group = new Chat();
		group.setGroup(true);
		group.setChat_image(req.getChat_image());
		group.setChat_name(req.getChat_name());
		group.setCreatedBy(reqUser);
		group.getAdmins().add(reqUser);
		
		// Modifying a Copy to add Login User ID
		req.getUserIds().add(reqUser.getId());		
		
		for(Long userId : req.getUserIds()) {
			User user = userService.findUserById(userId);
			group.getUsers().add(user);
			
			chatRepository.save(group);
		}
		
		return group;
	}

	@Override
	public Chat addUserToGroup(Long userId, Long chatId, User reqUser) throws UserException, ChatException {
		Optional<Chat> opt = chatRepository.findById(chatId);
		
		User user = userService.findUserById(userId);
		
		if(opt.isPresent()) {
			Chat chat = opt.get();
			
			if(chat.getAdmins().contains(reqUser)) {
				chat.getUsers().add(user);				
				return chatRepository.save(chat);
			} else {
				throw new UserException("You are not admin!");
			}
		}
		
		throw new ChatException("Chat not found with id: " + chatId);
	}

	@Override
	public Chat renameGroup(Long chatId, String groupName, User reqUser) throws UserException, ChatException {
		Optional<Chat> opt = chatRepository.findById(chatId);
		if(opt.isPresent()) {
			Chat chat = opt.get();
			
			if(chat.getUsers().contains(reqUser)) {
				chat.setChat_name(groupName);
				return chatRepository.save(chat);
			}
			throw new UserException("You are not memeber of this group");
		}
		throw new ChatException("Chat not found with id: " + chatId);
	}

	@Override
	public Chat removeFromGroup(Long chatId, Long userId, User reqUser) throws UserException, ChatException {
		Optional<Chat> opt = chatRepository.findById(chatId);
		
		User user = userService.findUserById(userId);
		
		if(opt.isPresent()) {
			Chat chat = opt.get();
			
			if(chat.getAdmins().contains(reqUser)) {
				chat.getUsers().remove(user);
				return chatRepository.save(chat);
				
			} else if(chat.getUsers().contains(reqUser)){
				if(user.getId().equals(reqUser.getId())) {
					chat.getUsers().remove(user);
					return chatRepository.save(chat);
				}
			}
			throw new UserException("You can't remove another user!");
		}
		
		throw new ChatException("Chat not found with id: " + chatId);
	}

	@Override
	public void deleteChat(Long chatId, Long userId) throws UserException, ChatException {
		Optional<Chat> opt = chatRepository.findById(chatId);
		if(opt.isPresent()) {
			Chat chat = opt.get();
			chatRepository.deleteById(chat.getId());
		}
	}

}
