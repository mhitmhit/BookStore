package com.fdmgroup.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.exceptions.UserNotFoundException;
import com.fdmgroup.bookstore.model.User;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
	
	@Mock
	UserRepository<User> mockUserRepository;
	
	@Test
	void test_AuthenticateMethod_UserIsInRepo(){
		
		AuthenticationService authSer = new AuthenticationService(mockUserRepository);
		User testUser = new User();
		User retUser = null;
		
		when(mockUserRepository.validate("test","test123")).thenReturn(true);
		when(mockUserRepository.findByUsername("test")).thenReturn(testUser);
		
		try {
			retUser = authSer.authenticate("test","test123");
		} catch (UserNotFoundException e) {
			System.out.println("test failed. user not found exception thrown");
		}
		
		assertEquals(retUser, testUser);
	}
	
	@Test
	void test_authenticateMethod_UserIsNotInRepo() {
		AuthenticationService authSer = new AuthenticationService(mockUserRepository);
		User retUser = null;
		Boolean result = true;
		when(mockUserRepository.validate("test","test123")).thenReturn(false);
		try {
			retUser = authSer.authenticate("test","test123");
		} catch (UserNotFoundException e) {
			result = false;
		}
		assertFalse(result);
	}
	
	@Test
	void test_findByIDMethod_UserIsInRepo() {
		
		AuthenticationService authSer = new AuthenticationService(mockUserRepository);
		User testUser = new User();
		User retUser = null;
		
		when(mockUserRepository.findById(123)).thenReturn(testUser);
		
		try {
			retUser = authSer.findById(123);
		} catch (UserNotFoundException e) {
			System.out.println("test failed. user not found exception thrown");
		}
		
		assertEquals(retUser, testUser);
	}
	
	@Test
	void test_findByIDMethod_UserIsNotInRepo() {
		AuthenticationService authSer = new AuthenticationService(mockUserRepository);
		User testUser = new User();
		User retUser = null;
		boolean result =false;
		
		when(mockUserRepository.findById(123)).thenReturn(null);
		
		try {
			retUser = authSer.findById(123);
		} catch (UserNotFoundException e) {
			result = true;
		}
		
		assertTrue(result);
	}	

}
