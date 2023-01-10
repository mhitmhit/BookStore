package com.fdmgroup.bookstore.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.model.User;

class UserArrayListRepositoryTest {
	
	@Test
	void testUserArrayListRepositoryCreatingSuccessfully_with3Users() {
		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
		
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
		
		assertEquals( 3, userArrayListRepository.findAll().size());
	}
	
	@Test
	void testUserArrayListRepositoryCreatingSuccessfully_withNoUsers() {
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
		
		assertEquals( 0, userArrayListRepository.findAll().size());
	}
	
	@Test
	void testUserArrayListRepositoryValidateMethod_WhenTheUserExistsInTheList() {
		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
		
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
		
		boolean result = userArrayListRepository.validate("user3UserName", "user3Password");
		
		assertTrue(result);
	}
	
	@Test
	void testUserArrayListRepositoryValidateMethod_WhenTheUserExistsInTheListButPasswordDoNotMatch() {
		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
		
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
		
		boolean result = userArrayListRepository.validate("user3UserName", "userPassword");
		
		assertFalse(result);
	}
	
	@Test
	void testUserArrayListRepositoryValidateMethod_WhenNoUserExistsInTheList() {
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
		
		boolean result = userArrayListRepository.validate("user3UserName", "user3Password");
		
		assertFalse(result);
	}
	
	@Test
	void testUserArrayListRepositoryFindByUsernameMethod_When3UserExistsInTheList() {
		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
		
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
		
		User returnedUser = userArrayListRepository.findByUsername("user1UserName");
		
		assertEquals(user1, returnedUser);
	}
	
	@Test
	void testUserArrayListRepositoryFindByUsernameMethod_WhenNoUserExistsInTheList() {
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
		
		User returnedUser = userArrayListRepository.findByUsername("user1UserName");
		
		assertEquals(null, returnedUser);
	}
	
//	@Test
//	void testUserArrayListRepositorySaveMethod_When3UserExistsInTheList() {
//		User user1 = new User(1, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
//		User user2 = new User(2, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
//		User user3 = new User(3, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
//		User user4 = new User(4, "user4first","user4last", "user4UserName", "user4Password", "user4Email@iclouud", null);
//		
//		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
//		List<User> userList2 = new ArrayList<User>(Arrays.asList(user1, user2, user3, user4)); 
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
//		
//		userArrayListRepository.save(user4);
//
//		assertEquals(userList2, userArrayListRepository.findAll());
//	}
	
//	@Test
//	void testUserArrayListRepositorySaveMethod_WhenNoUserExistsInTheList() {
//		User user3 = new User(3, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
//		User user4 = new User(4, "user4first","user4last", "user4UserName", "user4Password", "user4Email@iclouud", null);
//		
//		List<User> userList2 = new ArrayList<User>(Arrays.asList(user3, user4)); 
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
//
//		userArrayListRepository.save(user3);
//		userArrayListRepository.save(user4);
//
//		assertEquals(userList2, userArrayListRepository.findAll());
//	}
//	
//	@Test
//	void testUserArrayListRepositorySaveMethod_whenUserAlreadyExists() {
//		User user1 = new User(1, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
//		User user2 = new User(1, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
//
//		
//		List<User> userList2 = new ArrayList<User>(Arrays.asList(user2)); 
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
//
//		userArrayListRepository.save(user1);
//		userArrayListRepository.save(user2);
//
//		assertEquals(userList2, userArrayListRepository.findAll());
//	}
	
//	@Test
//	void testUserArrayListRepositorySaveMethod_whenInvalidIDpassedIn() {
//		User user1 = new User(0, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
//		User user2 = new User(-100, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
//		
//		List<User> userList2 = new ArrayList<User>(Arrays.asList(user1, user2)); 
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
//
//		userArrayListRepository.save(user1);
//		userArrayListRepository.save(user2);
//
//		assertEquals(userList2, userArrayListRepository.findAll());
//	}
	
//	@Test
//	void testUserArrayListRepositorySaveMethod_whenInvalidIDpassedInAndNextIDisInList() {
//		User user1 = new User(1, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
//		User user2 = new User(2, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
//		User user3 = new User(3, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
//		User user4 = new User(0, "user4first","user4last", "user4UserName", "user4Password", "user4Email@iclouud", null);
//		
//		List<User> userList2 = new ArrayList<User>(Arrays.asList(user1, user2, user3, user4)); 
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
//
//		
//		userArrayListRepository.save(user1);
//		userArrayListRepository.save(user2);
//		userArrayListRepository.save(user3);
//		userArrayListRepository.save(user4);
//		assertEquals(userList2, userArrayListRepository.findAll());
//	}	
	
	
	
	
	
	@Test
	void testUserArrayListRepositoryGenerateIdMethod() {
		UserArrayListRepository.generateId();
		UserArrayListRepository.generateId();
		int returnedValue = UserArrayListRepository.generateId();
		assertEquals(3, returnedValue);
	}
	
	@Test
	void testUserArrayListRepositoryFindByIdMethod_When3UsersExistsInTheList() {
		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
		
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
		
		User returnedUser = userArrayListRepository.findById(101);
		
		assertEquals(user1, returnedUser);
	}
	
	@Test
	void testUserArrayListRepositoryFindByIdMethod_WhenNoUsersExistsInTheList() {
		//User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
		
		User returnedUser = userArrayListRepository.findById(101);
		
		assertEquals(null, returnedUser);
	}
	
	@Test
	void testUserArrayListRepositoryFindAllMethod_When3UsersExistsInTheList() {
		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
		
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
		
		List<User> returnedUserList = userArrayListRepository.findAll();
		
		assertEquals(userList, returnedUserList);
	}
	
	@Test
	void testUserArrayListRepositoryFindAllMethod_WhenNoUsersExistsInTheList() {
		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
		List<User> userList = new ArrayList<User>();
		List<User> returnedUserList = userArrayListRepository.findAll();
		
		assertEquals(userList, returnedUserList);
	}
	
//	@Test
//	void testUserArrayListRepositoryDeleteMethod_When3UsersExistsInTheList() {
//		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
//		User user2 = new User(102, "user2first","user2last", "user2UserName", "user2Password", "user2Email@iclouud", null);
//		User user3 = new User(103, "user3first","user3last", "user3UserName", "user3Password", "user3Email@iclouud", null);
//		
//		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2, user3));
//		List<User> userList2 = new ArrayList<User>(Arrays.asList(user1, user3)); 
//		
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository(userList);
//
//		userArrayListRepository.delete(user2);
//		
//		assertEquals(userList2, userArrayListRepository.findAll());
//	}	
	
//	@Test
//	void testUserArrayListRepositoryDeleteMethod_WhenNoUsersExistsInTheList() {
//		UserArrayListRepository userArrayListRepository = new UserArrayListRepository();
//		User user1 = new User(101, "user1first","user1last", "user1UserName", "user1Password", "user1Email@iclouud", null);
//		
//		List<User> initialList = userArrayListRepository.findAll();
//		userArrayListRepository.delete(user1);
//		
//		assertEquals(initialList, userArrayListRepository.findAll());
//	}
//	
	
	
}
