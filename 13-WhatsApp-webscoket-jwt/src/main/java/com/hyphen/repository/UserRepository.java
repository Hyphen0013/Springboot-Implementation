package com.hyphen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hyphen.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.full_name LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(String query);
	
	@Query("SELECT u FROM User u WHERE (u.full_name LIKE CONCAT('%', :query, '%') OR u.email LIKE CONCAT('%', :query, '%')) AND u.email != :email")
	public List<User> searchUserNotLoggedIn(@Param("query") String query, @Param("email") String email);
}
