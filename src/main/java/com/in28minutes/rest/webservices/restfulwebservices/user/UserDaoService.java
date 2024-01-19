package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static int userCount =0;
	
	static {
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Riya", LocalDate.now().minusYears(23)));
		users.add(new User(++userCount, "Abhi", LocalDate.now().minusYears(18)));
	}
	
	public static List<User> findAll(){
		return users;
		
	}
	
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public User findById(int id){
		Predicate<? super User> predicate = user -> user.getId() == id ;
		return users.stream().filter(predicate).findFirst().orElse(null);
		
	}
	
	public void deleteById(int id){
		Predicate<? super User> predicate = user -> user.getId() == id ;
		users.removeIf(predicate);

	}
}
 