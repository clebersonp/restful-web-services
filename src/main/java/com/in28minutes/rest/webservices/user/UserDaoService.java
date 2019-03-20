package com.in28minutes.rest.webservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static Long countUsers = 3L;
	private static final List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1L, "Cleberson", LocalDate.of(1985, 3, 2), null));
		users.add(new User(2L, "Bruna", LocalDate.of(1984, 5, 10), null));
		users.add(new User(3L, "Regina", LocalDate.of(1973, 4, 15), null));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(final User user) {
		if (user != null) {
			if (user.getId() == null) {
				user.setId(UserDaoService.getNextVal());
			}
			users.add(user);
		}
		return user;
	}
	
	public Optional<User> findOne(final Long id) {
		return users.stream().filter(u -> u.getId().equals(id)).findFirst();
	}
	
	private static Long getNextVal() {
		return ++countUsers;
	}

	public boolean deleteBy(final Long id) {
		return users.removeIf(u -> u.getId().equals(id));
	}
}