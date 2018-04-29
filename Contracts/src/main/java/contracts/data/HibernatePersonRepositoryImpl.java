package contracts.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import contracts.domain.Person;

@Repository
@Transactional
public class HibernatePersonRepositoryImpl implements PersonRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Person> findByinitials(String name, String fathersName, String lastName) {
		CriteriaBuilder builder = entityManager.unwrap(Session.class).getCriteriaBuilder();
		// create criteria
		CriteriaQuery<Person> query = builder.createQuery(Person.class);
		// specify criteria root
		Root<Person> root = query.from(Person.class);
		query.select(root).where(builder.and(
				builder.equal(root.get("name"),  name),
				builder.equal(root.get("lastName"), lastName),
				builder.equal(root.get("fathersName"), fathersName)));
		List<Person> result = entityManager.unwrap(Session.class).createQuery(query).getResultList();
		return result;
	}

	@Override
	public void save(Person person) {
		entityManager.merge(person);
	}

	@Override
	public Person add(Person person) {
		entityManager.persist(person);
		return person;
	}

	@Override
	public List<Person> findAll() {
		CriteriaQuery<Person> criteria = entityManager.unwrap(Session.class).getCriteriaBuilder()
        		.createQuery(Person.class);
        criteria.from(Person.class);
        return entityManager.unwrap(Session.class).createQuery(criteria).getResultList();
	}

}
