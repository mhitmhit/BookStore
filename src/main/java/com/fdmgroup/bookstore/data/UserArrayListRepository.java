package com.fdmgroup.bookstore.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fdmgroup.bookstore.model.User;

public class UserArrayListRepository implements UserRepository {
	
	private List<User> users;
	public static int id=0;
	
	public UserArrayListRepository(List<User> users) {
		super();
		this.users = users;
	}

	public UserArrayListRepository() {
		super();
		this.users = new ArrayList<User>();
	}
	
	public static int generateId() {
		id = id + 1;
		return id;
	}

	@Override
	public User findById(int id) {
		User returnedUser = null;
		try {
			System.out.println(users.size());
			returnedUser = users.stream().filter( user -> user.getUserId() == id).collect(Collectors.toList()).get(0);
		}catch(Exception e) {
			System.out.println("no User found");
		}
		return returnedUser;
	}

	@Override
	public List<User> findAll() {
		return users;
	}

//	@Override
//	public User save(User user) {
//		List<Integer> existingIDs = users.stream().map(u -> u.getUserId()).collect(Collectors.toList());
//		int userId = user.getUserId();
//		boolean validID = userId > 0;
//		boolean idInList = existingIDs.contains(userId);
//		if (!validID) {
//			do {
//				user.setUserId(generateId());
//				userId = user.getUserId();
//				idInList = existingIDs.contains(userId);
//			} while(idInList);
//			users.add(user);
//			return user;
//		}else {
//			if (idInList) {
//				int indexOfRemove = existingIDs.indexOf(user.getUserId());
//				users.remove(indexOfRemove);
//				users.add(user);
//				return user;
//			} else {
//				users.add(user);
//				return user;
//			}
//		}
//	}

//	@Override
//	public void delete(User user) {
//		users.remove(user);
//	}

	@Override
	public boolean validate(String username, String password) {
		List<User> listOfUsersWithMatchingUserNames = users.stream().filter( user -> user.getUserName().equalsIgnoreCase(username)).collect(Collectors.toList());
		String existingUserPassword = null;
		try {
			existingUserPassword = listOfUsersWithMatchingUserNames.get(0).getPassword();
		} catch(Exception e) {
			
		}
		
		if (listOfUsersWithMatchingUserNames.size() != 0 && existingUserPassword == password) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User findByUsername(String username) {
		List<User> listOfUsersWithMatchingUserNames = null;
		listOfUsersWithMatchingUserNames = users.stream().filter( user -> user.getUserName().equalsIgnoreCase(username)).collect(Collectors.toList());
		if (listOfUsersWithMatchingUserNames.size() != 0) {
			return listOfUsersWithMatchingUserNames.get(0);
		}
		return null;
	}
	
}
