package br.com.cadsys.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import br.com.cadsys.exception.UserExistsException;
import br.com.cadsys.exception.UserInvalidException;
import br.com.cadsys.exception.UserUnAuthorizedException;
import br.com.cadsys.model.User;
import br.com.cadsys.service.UserService;
import br.com.cadsys.service.dto.LoginDTO;

@RestController
@RequestMapping("/api/user")
public class UserResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws UserExistsException, UserUnAuthorizedException {
		String token = request.getHeader("Authorization");
		User userSave = userService.saveUser(user, user.getPassword(), token);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, userSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
	}
	
	@PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> loginUser(@Valid @RequestBody LoginDTO userLogin) throws UserUnAuthorizedException, UserInvalidException {
		User user = userService.loginSystem(userLogin);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/codigo/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> searchUserById(@PathVariable String id, HttpServletRequest request) throws UserUnAuthorizedException {
		String token = request.getHeader("Authorization");
		User user = userService.searchIdUser(id, token);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/name/{name}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> searchUserByName(@PathVariable String name) {
		List<User> users = userService.listUserWithName(name);
		return users != null ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> searchAllUsers() {
		List<User> users = userService.listAllUsers();
		return users != null ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}
}
