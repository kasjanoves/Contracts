package contracts.data;

import java.util.List;

import contracts.domain.Person;

public interface PersonRepository {

	List<Person> findByinitials(String name, String fathersName, String lastName);
	
	List<Person> findAll();
	
	void save(Person person);
	
	Person add(Person person);
}
