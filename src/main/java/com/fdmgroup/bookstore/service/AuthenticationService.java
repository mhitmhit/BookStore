package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationService<T> {
	
	private UserRepository<T> userRepository;
	
	public AuthenticationService(UserRepository<T> userRepository) {
		this.userRepository = userRepository;
	}
	
	public User authenticate(String username, String password) {
		return null;
	}
	
	
}
