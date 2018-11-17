package br.com.cadsys.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.cadsys.config.Constants;
import br.com.cadsys.model.Permissao;
import br.com.cadsys.model.User;
import br.com.cadsys.repository.PermissaoRepository;
import br.com.cadsys.repository.UserRepository;
import br.com.cadsys.security.util.EncoderUtil;
import br.com.cadsys.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	public User saveUser(User user, String password) {
		
		user.setId(UUID.randomUUID().toString());
		user.getPhones().forEach(p -> p.setUser(user));
		
		Permissao authority = permissaoRepository.findOne(Constants.ROLE_ADMIN);
		List<Permissao> authoritys = Arrays.asList(authority);
		user.setPermissoes(authoritys);
		
		user.setPassword(EncoderUtil.PasswordEncode(password));
		
		return userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<User> listUserWithName(String name) {
		return userRepository.searchUserName("%" + name + "%");
	}

	@Transactional(readOnly = true)
	@Override
	public User searchIdUser(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}
}