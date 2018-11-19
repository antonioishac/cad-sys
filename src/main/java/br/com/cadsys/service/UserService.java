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
 * Assinatura dos métodos referente a usuário
 *
 */
public interface UserService {
	
	/**
	 * Método para persistir na base os novos usuários
	 * 
	 * @param user
	 * @param password
	 * @throws UserExistsException
	 * @throws UserUnAuthorizedException
	 * @return User
	 */
	public User saveUser(User user, String password) throws UserExistsException;
	
	/**
	 * Método que retorna uma lista de usuários pesquisando pelo nome
	 * 
	 * @param name
	 * @return List<User> 
	 */
	public List<User> listUserWithName(String name);
	
	/**
	 * Método que retorna um usuário quando pesquisado pelo teu identificador
	 * 
	 * @param id
	 * @param tokenHeader
	 * @throws UserUnAuthorizedException
	 * @return User
	 */
	public User searchIdUser(String id, String tokenHeader) throws UserUnAuthorizedException;
	
	/**
	 * Método que retorna uma lista de todos os usuários
	 * 
	 * @return List<User>
	 */
	public List<User> listAllUsers();
	
	/**
	 * Método que realiza o login no sistema
	 * 
	 * @param login
	 * @throws UserUnAuthorizedException
	 * @throws UserInvalidException
	 * @return User
	 */
	public User loginSystem(LoginDTO login) throws UserUnAuthorizedException, UserInvalidException;

}
