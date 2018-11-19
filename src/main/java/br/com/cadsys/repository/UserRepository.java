package br.com.cadsys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadsys.model.User;

/**
 * @author aishac
 * 
 * Representação da interface de usuário
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	/**
	 * Busca uma lista de usuários pelo nome passado como parametro
	 * 
	 * @param name
	 * @return List<User>
	 */
	@Query("SELECT u FROM User u WHERE u.name like :name")
	public List<User> searchUserName(@Param("name") String name);
	
	/**
	 * Busca na base de dados um usuário com o email passado como parametro
	 * 
	 * @param email
	 * @return User
	 */
	public User findByEmail(String email);
	
	/**
	 * Busca um usuário na base de dados com o token passado como parametro.
	 * 
	 * @param token
	 * @return User
	 */
	public User findByToken(String token);
	
}
