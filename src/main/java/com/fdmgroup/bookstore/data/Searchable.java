package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.User;

public interface Searchable {
	
	User findById(int id); // in diaghram it says (Id id). i changed it to (int id) to remove the error.
	List<User> findAll();
	
	
}
