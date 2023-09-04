package com.hyphen.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	private String content;

	private LocalDateTime timestamp;

	@ManyToOne
	private User user;

	@ManyToOne
	@JoinColumn(name = "chat_id")
	@JsonIgnore
	private Chat chat;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Long id, String content, LocalDateTime timestamp, User user, Chat chat) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
		this.user = user;
		this.chat = chat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

}
