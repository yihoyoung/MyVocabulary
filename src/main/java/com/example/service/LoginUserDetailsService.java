package com.example.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username){
		User user = userRepository.findOne(username);
		if (user == null){
			throw new UsernameNotFoundException("The requested user is not found.");
		}
		return new LoginUserDetails(user);
	}
	
	public User save(String username, String password){
		User newUser = null;
		User user = userRepository.findOne(username);
		if(user == null){
			newUser = new User(username, new BCryptPasswordEncoder().encode(password), null, null);
			newUser = userRepository.save(newUser);
		}else{
			throw new ServiceException("The user is exist.");
		}
		return newUser;
	}
}
