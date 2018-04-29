package contracts.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import contracts.data.PersonRepository;
import contracts.domain.Person;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = "http://127.0.0.1:8888")
public class PersonsRESTController {

	@Autowired
	PersonRepository repository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Person> persons(){
		return repository.findAll();
	}
	
	@RequestMapping(value="/getone", method=RequestMethod.GET)
	public ResponseEntity<List<Person>> getOne(String name, String fathersName, String lastName) {
		List<Person> persons = repository.findByinitials(name, fathersName, lastName);
		HttpStatus status = persons != null ?
				HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<List<Person>>(persons, status);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<Long> add(@RequestBody @Valid Person person) {
		Person persistedPerson = repository.add(person);
		return new ResponseEntity<Long>(persistedPerson.getId(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(@RequestBody @Valid Person person) {
		repository.save(person);
	}
	
}
