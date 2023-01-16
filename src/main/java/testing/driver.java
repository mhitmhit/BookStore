package testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fdmgroup.bookstore.data.UserArrayListRepository;
import com.fdmgroup.bookstore.exceptions.UserNotFoundException;
import com.fdmgroup.bookstore.model.AudioBook;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.EBook;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.BookService;

public class driver {

	public static void main(String[] args) throws UserNotFoundException {
		List<Order> list1 = new ArrayList<Order>();
		List<Order> list2 = new ArrayList<Order>();
		
		Book book1 = new AudioBook(101, 9.99, "the innocent man", "john grisham", BookGenre.Fiction, 5005);
		Book book2 = new EBook(102, 10.99, "david and goliath", "terrence florio", BookGenre.NONFiction, 3000);
		Book book3 = new Book(103, 11.99, "daily habits", "alex dutton", BookGenre.NONFiction);
		
		User user1 = new User(99, "test1", "testing1", "test1Username", "test1Password", "test1@gmail.com", list1 );
		User user2 = new User(98, "test2", "testing2", "test2Username", "test2Password", "test2@gmail.com", list2);
		
		Order order1 = new Order(4567, book1, user1, LocalDateTime.now());
		Order order2 = new Order(5678, book2, user2, LocalDateTime.now());
		Order order3 = new Order(6789, book3, user2, LocalDateTime.now());
		
		list1.add(order1);
		list2.add(order2);
		list2.add(order3);
		
		user1.setOrders(list1);
		user2.setOrders(list2);
		
		System.out.println(user1);
		
		List<User> userList = new ArrayList<User>(Arrays.asList(user1, user2));
		
		UserArrayListRepository userListRepo = new UserArrayListRepository(userList);
		System.out.println("-------------------------------------------------------");
		
//		System.out.println(
//				userListRepo.findById(98).toString()
//				);
		
		//System.out.println(userListRepo.findAll().toString());
		
		//System.out.println(userListRepo.validate("test1Username", "test1Passwor"));
		
		//System.out.println(userListRepo.findByUsername("test2Username"));
		
		System.out.println("---------------------------------------------------------------------------AuthService");
		
		AuthenticationService authService = new AuthenticationService(userListRepo);
		
		User retUser = authService.authenticate("test2Username", "test2Password");
		//User retUser2 = authService.findById(9);
		//System.out.println(retUser2);
		
		System.out.println("---------------------------------------------------------------------------BookService");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
