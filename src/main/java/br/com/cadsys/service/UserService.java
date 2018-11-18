package br.com.cadsys.service;

import java.util.List;

import br.com.cadsys.exception.UserExistsException;
import br.com.cadsys.exception.UserInvalidException;
import br.com.cadsys.exception.UserUnAuthorizedException;
import br.com.cadsys.model.User;
import br.com.cadsys.service.dto.LoginDTO;

/**
 * @author aishac
 *
 */
public interface UserService {
	
	public User saveUser(User user, String password, String tokenRequisicao) throws UserExistsException, UserUnAuthorizedException;
	
	public List<User> listUserWithName(String name);
	
	public User searchIdUser(String id, String tokenHeader) throws UserUnAuthorizedException;
	
	public List<User> listAllUsers();
	
	public User loginSystem(LoginDTO login) throws UserUnAuthorizedException, UserInvalidException;

}
