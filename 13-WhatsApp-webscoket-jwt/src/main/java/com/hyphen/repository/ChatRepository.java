package com.hyphen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hyphen.model.Chat;
import com.hyphen.model.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	@Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
	public List<Chat> findChatByUserId(@Param("userId") Long userId);
	

	@Query("SELECT c FROM Chat c WHERE c.isGroup=false AND :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
	public Chat findSingleChatByUserIds(
		@Param("user") User user,
		@Param("reqUser") User reqUser
	);
}
