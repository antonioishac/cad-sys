package br.com.cadsys.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadsys.config.Constants;
import br.com.cadsys.exception.UserExistsException;
import br.com.cadsys.exception.UserInvalidException;
import br.com.cadsys.exception.UserUnAuthorizedException;
import br.com.cadsys.model.Permissao;
import br.com.cadsys.model.User;
import br.com.cadsys.repository.PermissaoRepository;
import br.com.cadsys.repository.UserRepository;
import br.com.cadsys.service.UserService;
import br.com.cadsys.service.dto.LoginDTO;
import br.com.cadsys.service.util.GeneratePassword;

/**
 * @author aishac
 * 
 * Classe de serviço de usuário
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	/**
	 * Constante parametrizada com o tempo de validade do token
	 */
	private static final int TEMPO_VALIDADE = 1800;
	
	/**
	 * Injeção de dependência para o repositório de usuário
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Injeção de dependências para o repositório de permissão de usuário
	 */
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public User saveUser(User user, String password) throws UserExistsException {
		
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserExistsException("E-mail já existente");
		}
		
		user.setId(UUID.randomUUID().toString().substring(0, 7));
		user.getPhones().forEach(p -> p.setUser(user));
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		
		Permissao authority = permissaoRepository.findOne(Constants.ROLE_ADMIN);
		List<Permissao> authoritys = Arrays.asList(authority);
		user.setPermissoes(authoritys);
		
		user.setToken(generateToken());
		user.setPassword(GeneratePassword.cryptPassword(password));	
		
		return userRepository.save(user);
	}	
	
	private String generateToken() {
		return UUID.randomUUID().toString();
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> listUserWithName(String name) {
		return userRepository.searchUserName("%" + name + "%");
	}

	@Transactional(readOnly = true)
	@Override
	public User searchIdUser(String id, String tokenHeader) throws UserUnAuthorizedException {		
		User user = userRepository.findOne(id);
		verificaToken(tokenHeader);
		return user;
	}

	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User loginSystem(LoginDTO login) throws UserUnAuthorizedException, UserInvalidException {
		User user = userRepository.findByEmail(login.getEmail());
		if (user != null) {
			if (GeneratePassword.checkPass(login.getPassword(), user.getPassword())) {
				user.setLastLogin(LocalDateTime.now());				
				user = userRepository.save(user);				
				return user;
			} else {
				throw new UserUnAuthorizedException("Usuário e/ou senha inválidos");
			}
		} else {
			throw new UserInvalidException("Usuário e/ou senha inválidos");
		}
	}
	
	/**
	 * Método que realiza as seguintes operações, 
	 * buscar o token do usuario e verificar se o usuario está com o tempo de validade e se possui o perfil de admin,
	 * verifica se o token está no tempo de validade.
	 * 
	 * @param tokenHeader
	 * @throws UserUnAuthorizedException
	 */
	private void verificaToken(String tokenHeader) throws UserUnAuthorizedException {		
		User userToken = userRepository.findByToken(tokenHeader);		
		if (userToken != null) {			
			for(Permissao p : userToken.getPermissoes()) {
				if (Constants.ROLE_ADMIN == p.getId()) {					
					LocalDateTime dateTime = LocalDateTime.now();
					Duration diferencaDeTempo = Duration.between(userToken.getLastLogin(), dateTime);					
					if (diferencaDeTempo.getSeconds() > TEMPO_VALIDADE) {
						throw new UserUnAuthorizedException("Sessão inválida");
					}
					return;
				} else {
					throw new UserUnAuthorizedException("Não Autorizado");
				}
			}
		} else {
			throw new UserUnAuthorizedException("Não Autorizado");
		}
	}
}