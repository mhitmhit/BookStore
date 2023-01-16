package com.fdmgroup.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.exceptions.ItemNotFoundException;
import com.fdmgroup.bookstore.exceptions.UserNotFoundException;
import com.fdmgroup.bookstore.model.AudioBook;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
//import com.fdmgroup.bookstore.model.EBook;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
	
	@Mock
	BookRepository<Book> mockBookRepository;
	
	@Mock
	UserRepository<User> mockUserRepository;
	
	@Mock
	OrderRepository<Order> mockOrderRepository;
	
	@Test
	void test_placeOrderMethod(){
		//OrderingService orderingService1 = new OrderingService(mockOrderRepository);
		OrderingService orderingService = new OrderingService(mockOrderRepository, mockBookRepository, mockUserRepository);
		
		User user1 = new User(99, "test1", "testing1", "test1Username", "test1Password", "test1@gmail.com", null );
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		//Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		//Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		//List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1, book2, book3));
		
		Order exptectedOrder = new Order(101, book1, user1, LocalDateTime.now());
		
		when(mockBookRepository.findById(101)).thenReturn(book1);
		when(mockUserRepository.findById(99)).thenReturn(user1);
		
		try {
			assertEquals(orderingService.placeOrder(book1, user1), exptectedOrder);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void test_placeOrderMethod_nullUser(){
		OrderingService orderingService = new OrderingService(mockOrderRepository, mockBookRepository, mockUserRepository);
		boolean result = false;
		User user1 = new User(99, "test1", "testing1", "test1Username", "test1Password", "test1@gmail.com", null );
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		//Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		//Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		//List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1, book2, book3));
		
		//Order exptectedOrder = new Order(101, book1, user1, LocalDateTime.now());
		
		when(mockBookRepository.findById(101)).thenReturn(book1);
		when(mockUserRepository.findById(99)).thenReturn(null);
		
		try {
			orderingService.placeOrder(book1, user1);
		} catch (UserNotFoundException e) {
			result = true;
			
		} catch (ItemNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		assertTrue(result);
	}	
	
	@Test
	void test_placeOrderMethod_nullBook(){
		OrderingService orderingService = new OrderingService(mockOrderRepository, mockBookRepository, mockUserRepository);
		boolean result = false;
		User user1 = new User(99, "test1", "testing1", "test1Username", "test1Password", "test1@gmail.com", null );
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		//Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		//Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		//List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1, book2, book3));
		
		//Order exptectedOrder = new Order(101, book1, user1, LocalDateTime.now());
		
		when(mockBookRepository.findById(101)).thenReturn(null);
		when(mockUserRepository.findById(99)).thenReturn(user1);
		
		try {
			orderingService.placeOrder(book1, user1);
		} catch (UserNotFoundException e) {
			
			
		} catch (ItemNotFoundException e) {
			// TODO Auto-generated catch block
			result = true;
		}
		
		assertTrue(result);
	}	
	
	@Test
	void test_placeOrdersMethod(){
		OrderingService orderingService = new OrderingService(mockOrderRepository, mockBookRepository, mockUserRepository);
		//boolean result = false;
		User user1 = new User(99, "test1", "testing1", "test1Username", "test1Password", "test1@gmail.com", null );
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		//Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		//Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1));
		 
		Order exptectedOrder = new Order(101, book1, user1, LocalDateTime.now());
		
		List<Order> listOrders = new ArrayList<Order>(Arrays.asList(exptectedOrder));
		
		when(mockUserRepository.findById(99)).thenReturn(user1);
		when(mockBookRepository.findById(101)).thenReturn(book1);
		
		
		try {
			assertEquals(listOrders, orderingService.placeOrders(list1, user1));
		} catch (UserNotFoundException e) {
		}
			
		
	}	


}
