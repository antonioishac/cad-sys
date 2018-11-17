package br.com.cadsys.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadsys.event.RecursoCriadoEvent;
import br.com.cadsys.model.User;
import br.com.cadsys.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, HttpServletResponse response) {
		User userSave = userService.saveUser(user, user.getPassword());
		publisher.publishEvent(new RecursoCriadoEvent(this, response, userSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
	}

	@GetMapping("/codigo/{id}")
	public ResponseEntity<User> searchUserById(@PathVariable String id) {
		User user = userService.searchIdUser(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> searchUserByName(@PathVariable String name) {
		List<User> users = userService.listUserWithName(name);
		return users != null ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<User>> searchAllUsers() {
		List<User> users = userService.listAllUsers();
		return users != null ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}
}
