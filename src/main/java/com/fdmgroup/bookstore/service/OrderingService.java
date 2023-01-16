package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.exceptions.ItemNotFoundException;
import com.fdmgroup.bookstore.exceptions.UserNotFoundException;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderingService {
	
	private OrderRepository<Order> orderRepository;
	private BookRepository<Book> bookRepository;
	private UserRepository<User> userRepository;
	
	public OrderingService(OrderRepository<Order> orderRepo, BookRepository<Book> bookRepo, UserRepository<User> userRepo){
		this.orderRepository = orderRepo;
		this.bookRepository = bookRepo;
		this.userRepository = userRepo;
	}
	
	public OrderingService(OrderRepository<Order> orderRepository){
		this.orderRepository = orderRepository;
	}
	
	public Order placeOrder(Book book, User customer) throws UserNotFoundException, ItemNotFoundException{
		Book returnedBook = (Book) bookRepository.findById(book.getItemId());
		User returnedUser = (User) userRepository.findById(customer.getUserId());
		if (returnedUser == null) {
			throw new UserNotFoundException("user not found during placement of order");
		}
		if (returnedBook == null) {
			throw new ItemNotFoundException("book not found during placement of order");
		}
		Order order = new Order(101, book, customer, LocalDateTime.now());
		return order;
	}
	
	public List<Order> placeOrders(List<Book> books, User customer) throws UserNotFoundException{
		List<Order> orderList = new ArrayList<Order>();
		User returnedUser = (User) userRepository.findById(customer.getUserId());
		if (returnedUser == null) {
			throw new UserNotFoundException("user not found during placement of order");
		}
		for (Book b:books) {
			Book returnedBook = (Book) bookRepository.findById(b.getItemId());
			if (!returnedBook.equals(null)) {
				orderList.add(new Order(101, b, customer, LocalDateTime.now()));
			}
		}
		return orderList;
	}
	
	public List<Order> getOrdersForUser(User user){
		return user.getOrders();
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrdersForBook(Book book){
		return (List<Order>) orderRepository.findById(book.getItemId());
	}
	
}
