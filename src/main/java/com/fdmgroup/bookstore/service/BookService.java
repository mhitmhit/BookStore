package com.fdmgroup.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.exceptions.ItemNotFoundException;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}
	
	public List<Book> getBooksOfGenre(BookGenre bookGenre){
		List<Book> returnedList = (List<Book>) bookRepository.findAll().stream().filter(bk -> ((Book) bk).getBookGenre().equals(bookGenre)).collect(Collectors.toList());
		return (List<Book>) returnedList;
	}
	
	public List<Book> searchBooksByTitle(String title){
		List<Book> returnedList = (List<Book>) bookRepository.findAll().stream().filter(bk -> ((Book) bk).getTitle().equals(title)).collect(Collectors.toList());
		return (List<Book>) returnedList;
	}
	
	public List<Book> searchBooksByAuthorName(String bookAuthorNameToSearch) {
		List<Book> returnedList = (List<Book>) bookRepository.findAll().stream().filter(bk -> ((Book) bk).getAuthor().contains(bookAuthorNameToSearch)).collect(Collectors.toList());
		return (List<Book>) returnedList;
	}
	
	// added this to complete Phase2 task 4 2.e
	public Book findById(int id) throws ItemNotFoundException {
		Book returnedBook = null;
		returnedBook = (Book) bookRepository.findById(id);
		if (returnedBook == null) {
			throw new ItemNotFoundException("User not Found");
		}
		return returnedBook;
	}
}
