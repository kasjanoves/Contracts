package contracts.data;

import java.util.List;

import contracts.domain.Contract;

public interface ContractRepository {
	
	Contract findOne(long number);
	
	List<Contract> findAll();
	
	void save(Contract contract);
	
	Contract add(Contract contract);

}
