package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService userDaoService;

	public UserResource(UserDaoService userDaoService) {
		super();
		this.userDaoService = userDaoService;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User>  retrieveUserById(@PathVariable int id)  {
		User user =  userDaoService.findById(id);
		
		if(user==null)
			throw new UserNotFoundException("id:" + id);
			EntityModel<User>  entityModel = EntityModel.of(user);
			 WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			 entityModel.add(link.withRel("all-users"));
			return entityModel;
		}

	// POST / users

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		userDaoService.save(user);

		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				        .path("/{id}")
				        .buildAndExpand(savedUser.getId())
				        .toUri();
		return ResponseEntity.created(location ).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id)  {
		userDaoService.deleteById(id);
		
		
		}

}
