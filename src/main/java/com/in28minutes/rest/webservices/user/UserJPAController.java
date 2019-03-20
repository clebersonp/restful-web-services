package com.in28minutes.rest.webservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.post.Post;
import com.in28minutes.rest.webservices.post.PostRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = { "/jpa/users" })
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	@GetMapping(path = "/{id}/posts")
	@ApiOperation(value = "Retorna os post de um determinado usuário")
	public List<Post> getUserPosts(@PathVariable final Long id) {
		Optional<User> userOptional = this.userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.format("User id - %d", id));
		}
		return userOptional.get().getPosts();
	}
	
	@GetMapping(path = "/{id}")
	public Resource<User> getUserById(@PathVariable final Long id) {
		final Optional<User> userOptional = this.userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.format("User id - %d", id));
		}
		
		final User user = userOptional.get();
		
		Resource<User> resource = new Resource<>(user);
		Link linkToAllUsers = linkTo(methodOn(this.getClass()).getUsers()).withRel("all-users");
		Link linkToDeleteUser = linkTo(methodOn(this.getClass()).deleteUserBy(user.getId())).withRel("delete-user");
		
		resource.add(linkToAllUsers, linkToDeleteUser);
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveUser(@Valid @RequestBody final User user) {
		User userSaved = this.userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = "{id}/posts")
	@ApiOperation(value = "Salvar o post de um usuario especifico")
	public ResponseEntity<Object> savePost(@PathVariable(name = "id") final Long idUsuario, @RequestBody final Post post) {
		Optional<User> userOptional = this.userRepository.findById(idUsuario);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.format("User id - %d", idUsuario));
		}
		
		final User user = userOptional.get();
		
		post.setUser(user);
		
		final Post postSaved = this.postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUserBy(@PathVariable final Long id) {
		Optional<User> userOptional = this.userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException(String.format("User id - %d", id));
		}
		
		this.userRepository.delete(userOptional.get());
		return ResponseEntity.noContent().build();
	}
}