package com.hyphen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hyphen.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	@Query("SELECT m FROM Message m JOIN m.chat c WHERE c.id = :chatId")
	public List<Message> findByChatId(
		@Param("chatId") Long chatId
	);
}
