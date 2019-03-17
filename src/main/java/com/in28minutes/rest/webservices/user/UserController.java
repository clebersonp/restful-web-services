package com.in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		return this.service.findOne(id);
	}
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody final User user) {
		User userSaved = this.service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}