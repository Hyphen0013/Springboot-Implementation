package com.hyphen.service.implementation;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hyphen.dto.LoginDTO;
import com.hyphen.dto.UserDTO;
import com.hyphen.entity.User;
import com.hyphen.repository.UserRepository;
import com.hyphen.response.LoginResponse;
import com.hyphen.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String addUser(UserDTO userDTO) {
		String mobile = "7854125632";
		Calendar calendar = Calendar.getInstance();
		Date createdAt = new Date(calendar.getTime().getTime());
		
		// INSERT WITH THE HELP OF CONSTRUCTOR ARGUMENT - CONSTRUCTOR INJECTION (Sequence should be matching)
		/**
		userDTO.setMobile(mobile);
		userDTO.setRole_type(2);
		userDTO.setRole("ROLE_USER");
		userDTO.setCreated_at(createdAt);
		User user = new User(
				userDTO.getEmail(),
				userDTO.getName(),
				this.passwordEncoder.encode(userDTO.getPassword()),
				userDTO.getMobile(),
				userDTO.getRole_type(),
				userDTO.getRole(),
				userDTO.getCreated_at()
		);
		*/
		
		// INSERT WITH THE HELP OF GETTER AND SETTER (not matter of sequence)
		
		User user = new User();
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
		user.setMobile(mobile);
		user.setRole_type(2);
		user.setRole("ROLE_USER");
		user.setCreated_at(createdAt);
		 
		userRepository.save(user);
		return user.getName();
	}

	@Override
	public LoginResponse loginUser(LoginDTO loginDTO) {
		String message = "";
		User user1 = userRepository.findByEmail(loginDTO.getEmail());
		if(user1 != null) {
			String password = loginDTO.getPassword();
			String encodePassword = user1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
			
			if(isPwdRight) {
				Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodePassword);
				if(user.isPresent()) {
					return new LoginResponse("Login Success", true);
				} else {
					return new LoginResponse("Login Failed", false);
				}
			} else {
				return new LoginResponse("Password Not Match", false);
			}
		} else {
			return new LoginResponse("Email Not Exists", false);
		}
	}
}
