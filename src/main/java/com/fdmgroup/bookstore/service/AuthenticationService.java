package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationService<T> {
	
	private UserRepository<T> userRepository;
	
	public AuthenticationService(UserRepository<T> userRepository) {
		this.userRepository = userRepository;
	}
	
	public User authenticate(String username, String password) throws UserNotFoundException {
		if(userRepository.validate(username, password)) {
			User returnedUser = userRepository.findByUsername(username);
			return returnedUser;
		}else {
			throw new UserNotFoundException("User not Found");
		}
	}
	// added this myself to do phase2 1.b. findById
	public User authenticate(int id) throws UserNotFoundException {
		T returnedUser = null;
		returnedUser = userRepository.findById(id);
		if (returnedUser == null) {
			throw new UserNotFoundException("User not Found");
		}
		return (User) returnedUser;
	}
	
}
