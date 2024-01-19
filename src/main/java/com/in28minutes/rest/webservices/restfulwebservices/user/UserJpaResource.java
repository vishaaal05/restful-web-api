package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserDaoService userDaoService;
	
	private UserRepository userRepository;
	
	

	public UserJpaResource( UserRepository userRepository) {
		super();

		this.userRepository = userRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User>  retrieveUserById(@PathVariable int id, @Valid RequestBody requestBody)  {
	Optional<User> user =  userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" + id);
			EntityModel<User>  entityModel = EntityModel.of(user.get());
			 WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			 entityModel.add(link.withRel("all-users"));
			return entityModel;
		}

	// POST / users

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		userDaoService.save(user);

		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				        .path("/{id}")
				        .buildAndExpand(savedUser.getId())
				        .toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id)  {
		userRepository.deleteById(id);
		
		
		}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id)  {
	Optional<User> user =  userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		
	 return	user.get().getPosts();
		}

}
