package br.com.cadsys.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadsys.model.User;

/**
 * @author aishac
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u FROM User u WHERE u.name like :name")
	public List<User> searchUserName(@Param("name") String name);
	
	public Optional<User> findByEmail(String email);
	
}
