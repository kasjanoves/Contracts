package contracts.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import contracts.domain.Contract;

@Repository
@Transactional
public class HibernateContractRepositoryImpl implements ContractRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Contract findOne(long number) {
		return entityManager.find(Contract.class, number);
	}

	@Override
	public List<Contract> findAll() {
        CriteriaQuery<Contract> criteria = entityManager.unwrap(Session.class).getCriteriaBuilder()
        		.createQuery(Contract.class);
        criteria.from(Contract.class);
        return entityManager.unwrap(Session.class).createQuery(criteria).getResultList();
	}

	@Override
	public void save(Contract contract) {
		entityManager.merge(contract);
	}

	@Override
	public Contract add(Contract contract) {
		entityManager.persist(contract);
		return contract;
	}

}
