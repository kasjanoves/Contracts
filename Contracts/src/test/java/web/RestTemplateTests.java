package web;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import contracts.domain.Person;

public class RestTemplateTests {
	
	private RestTemplate restTemplate;
	private Person policyHolder = new Person("John", "Doe", "", new Date(), 1, 1);
	
	@Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
    }

	@Test
	public void RestTemplateTest() {
		
		ResponseEntity<Person> response = restTemplate.postForEntity("http://localhost:8080/persons/add",
				policyHolder, Person.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(policyHolder, response.getBody());	
	}
	
}
