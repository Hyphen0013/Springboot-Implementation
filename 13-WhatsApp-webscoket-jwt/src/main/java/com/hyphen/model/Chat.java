package com.hyphen.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String chat_name;
	private String chat_image;

	@ManyToMany
	private Set<User> admins = new HashSet<>();

	@Column(name = "is_group")
	private boolean isGroup;

	@JoinColumn(name = "created_by")
	@ManyToOne
	private User createdBy;

//	@JoinTable(
//	    name = "chat_user", 
//	    joinColumns = @JoinColumn(name = "chat_id"), 
//	    inverseJoinColumns = @JoinColumn(name = "user_id")
//	)	
	@ManyToMany
	private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "chat")
	private List<Message> messages = new ArrayList<>();

	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chat(Long id, String chat_name, String chat_image, Set<User> admins, boolean isGroup, User createdBy,
			Set<User> users, List<Message> messages) {
		super();
		this.id = id;
		this.chat_name = chat_name;
		this.chat_image = chat_image;
		this.admins = admins;
		this.isGroup = isGroup;
		this.createdBy = createdBy;
		this.users = users;
		this.messages = messages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChat_name() {
		return chat_name;
	}

	public void setChat_name(String chat_name) {
		this.chat_name = chat_name;
	}

	public String getChat_image() {
		return chat_image;
	}

	public void setChat_image(String chat_image) {
		this.chat_image = chat_image;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Set<User> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<User> admins) {
		this.admins = admins;
	}
}
