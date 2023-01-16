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

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.exceptions.ItemNotFoundException;
import com.fdmgroup.bookstore.exceptions.UserNotFoundException;
import com.fdmgroup.bookstore.model.AudioBook;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.EBook;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	
	@Mock
	BookRepository mockBookRepository;
	
	@Mock
	BookService mockBookService;
	
	@Test
	void test_getAllBooksMethod_booksExistInRepo(){
		BookService bookService = new BookService(mockBookRepository);
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1, book2, book3));
		
		when(mockBookRepository.findAll()).thenReturn(list1);
		
		assertEquals(list1, bookService.getAllBooks());
	}
	
	@Test
	void test_getBooksOfGenreMethod_validGenrePassedIn() {
		BookService bookService = new BookService(mockBookRepository);
		BookGenre bookGenre = BookGenre.Fiction;
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1));
		
		when(mockBookRepository.findAll()).thenReturn(list1);
		
		assertEquals(list1, bookService.getBooksOfGenre(bookGenre));
		
	}
	
	@Test
	void test_searchBookByTitleMethod_validTitlePassedIn() {
		BookService bookService = new BookService(mockBookRepository);
		String bookTitle = "the innocent man";
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1));
		
		when(mockBookRepository.findAll()).thenReturn(list1);
		
		assertEquals(list1, bookService.searchBooksByTitle(bookTitle));
		
	}
	
	@Test
	void test_searchBookByAuthorMethod_validAuthorPassedIn() {
		BookService bookService = new BookService(mockBookRepository);
		String bookAuthor = "john";
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "john grisham", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book1, book3));
		
		when(mockBookRepository.findAll()).thenReturn(list1);
		
		assertEquals(list1, bookService.searchBooksByAuthorName(bookAuthor));
		
	}	
	
	@Test
	void test_findByIdMethod_validID() {
		BookService bookService = new BookService(mockBookRepository);
		int bookID = 103;
		boolean res = false;
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "john grisham", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book3));
		
		when(mockBookRepository.findById(103)).thenReturn(book3);
		
		try {
			assertEquals(book3, bookService.findById(103));
		} catch (ItemNotFoundException e) {
			res = true;
		}
	}		
	
	@Test
	void test_findByIdMethod_InValidID() {
		BookService bookService = new BookService(mockBookRepository);
		int bookID = 10;
		boolean res = false;
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "john grisham", BookGenre.NONFiction);
		List<Book> list1 = new ArrayList<Book>(Arrays.asList(book3));
		
		when(mockBookRepository.findById(10)).thenReturn(null);
		
		try {
			bookService.findById(10);
		} catch (ItemNotFoundException e) {
			res = true;
		}
		
		assertTrue(res);
	}	


}
