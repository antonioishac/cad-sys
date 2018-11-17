package br.com.cadsys.service;

import java.util.List;

import br.com.cadsys.model.User;

/**
 * @author aishac
 *
 */
public interface UserService {
	
	public User saveUser(User user, String password);
	
	public List<User> listUserWithName(String name);
	
	public User searchIdUser(String id);
	
	public List<User> listAllUsers();

}
