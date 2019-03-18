package com.in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = { "/users" })
public class UserController {

	@Autowired
	private UserDaoService service;
	
	@GetMapping
	public List<User> getUsers() {
		return this.service.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public User getUserById(@PathVariable final Long id) {
		final Optional<User> userOptional = this.service.findOne(id);
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.format("User id - %d", id));
		}
		return userOptional.get();
	}
	
	@PostMapping
	public ResponseEntity<Object> saveUser(@Valid @RequestBody final User user) {
		User userSaved = this.service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUserBy(@PathVariable final Long id) {
		
		if (!this.service.deleteBy(id)) {
			throw new UserNotFoundException(String.format("User id - %d", id));
		}
		return ResponseEntity.noContent().build();
	}
}