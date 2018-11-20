package br.com.cadsys;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadsys.model.Permissao;
import br.com.cadsys.model.Phone;
import br.com.cadsys.model.User;
import br.com.cadsys.repository.UserRepository;
import br.com.cadsys.service.util.GeneratePassword;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserResourceTests {
	
	final String BASE_PATH = "http://localhost:8081/api/user";

	@Autowired
	private UserRepository repository;

	private RestTemplate restTemplate;

	private ObjectMapper MAPPER = new ObjectMapper();

	@Before
    public void setUp() throws Exception {
		
		User user = new User();
		user.setId("CCDDEE");
		user.setEmail("usertest@test.com.br");
		user.setName("Test API");
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setPassword(GeneratePassword.cryptPassword("123"));
		user.setToken(UUID.randomUUID().toString());
		//user.setPermissoes(permissoes());
		
		user.setPhones(userPhones());
		user.getPhones().forEach(p -> p.setUser(user));
    
		repository.save(user);        
        
        restTemplate = new RestTemplate();
    }
	
	@Test
	public void testCreateUser() throws JsonProcessingException {

		// Criamos uma novo usuario
		User user = new User();
		user.setId("AABBCC");
		user.setEmail("user@test.com.br");
		user.setName("User API");
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setPassword(GeneratePassword.cryptPassword("123"));
		user.setToken(UUID.randomUUID().toString());
		user.setPermissoes(permissoes());
		
		user.setPhones(userPhones());
		user.getPhones().forEach(p -> p.setUser(user));
		User response = restTemplate.postForObject(BASE_PATH, user, User.class);

		Assert.assertEquals("User API", response.getName());
	}
	
	@Test(expected = JsonMappingException.class)
	public void testFindOne() throws JsonParseException, JsonMappingException, IOException {	 
	    String response = restTemplate.getForObject(BASE_PATH, String.class);	 
	    List<User> users = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, User.class));
	    User user = restTemplate.getForObject(BASE_PATH + "/codigo/" + users.get(0).getId(), User.class);	     
	    Assert.assertNotNull(user);
	    Assert.assertEquals("Test API", user.getName());	    
	}
	
	private List<Phone> userPhones() {
		List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setDdd("11");
		phone.setPhoneNumber("987654321");		
		phones.add(phone);		
		return phones;
	}
	
	private List<Permissao> permissoes() {
		Permissao p = new Permissao();
		p.setDescricao("ROLE_ADMIN");
		p.setId(1);
		List<Permissao> list = Arrays.asList(p);
		return list;		
	}
}
