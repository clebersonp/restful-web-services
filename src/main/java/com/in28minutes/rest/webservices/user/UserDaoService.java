package com.in28minutes.rest.webservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static Long countUsers = 3L;
	private static final List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1L, "Cleberson", LocalDate.of(1985, 3, 2)));
		users.add(new User(2L, "Bruna", LocalDate.of(1984, 5, 10)));
		users.add(new User(3L, "Regina", LocalDate.of(1973, 4, 15)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if (user != null) {
			if (user.getId() == null) {
				user.setId(UserDaoService.getNextVal());
			}
			users.add(user);
		}
		return user;
	}
	
	public User findOne(Long id) {
		return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
	}
	
	private static Long getNextVal() {
		return ++countUsers;
	}
}