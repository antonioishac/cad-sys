package br.com.cadsys;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cadsys.model.User;
import br.com.cadsys.repository.UserRepository;

//Inicializamos o SpringRunner
@RunWith(SpringRunner.class)
//Setamos como uma classe de testes com o Server inicializando em uma porta fixa
//Essa porta pode ser a 8080 ou a que desejarmos setada no nosso aplication.properties
//Acesse src/test/resources/application.properties caso deseje alterar
//No nosso caso é a 8888 como você pode ver no aplication.properties "server.port = 8888"
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserResourceTests {

	//URL base para onde as requests serão feitas
    final String BASE_PATH = "http://localhost:8888/person";
 
    //Injetamos o repositório para acesso ao Banco de dados
    @Autowired
    private UserRepository repository;
     
    //Definimos o restTemplate
    private RestTemplate restTemplate;
     
    //Definimos o JacksonMapper responsável por converter
    //JSON para Objeto e vice versa
    private ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void testCreatePerson() throws JsonProcessingException {
 
        //Criamos uma nova pessoa
        User user = new User();
 
        //Fazemos um HTTP request do tipo POST passando a pessoa como parâmetro
        User response = restTemplate.postForObject(BASE_PATH, user, User.class);
 
        //Verificamos se o resultado da requisição é igual ao esperado
        //Se sim significa que tudo correu bem
        Assert.assertEquals("Leandro Costa", response.getName() + " " + response.getCreated());
    }
}
