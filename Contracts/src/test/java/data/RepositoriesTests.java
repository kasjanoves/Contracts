package data;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import contracts.SpringBootWebApplication;
import contracts.data.ContractRepository;
import contracts.data.PersonRepository;
import contracts.domain.Adress;
import contracts.domain.Calculation;
import contracts.domain.Contract;
import contracts.domain.DefCalculationImpl;
import contracts.domain.EstateType;
import contracts.domain.Person;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootWebApplication.class)
public class RepositoriesTests {
	
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	Person policyHolder = new Person("John", "Doe", "", new Date(), 1, 1);

	@Test
	public void ContractRepositoryTest() {
		
		Adress adress = new Adress("USA", "111222", "New York city", "", "", "Elm str", 34, "", "", 66);
		EstateType propertyType = new EstateType("Квартира", 1.7f);
		Calculation calc = new DefCalculationImpl(new Date(), new Date(), propertyType, "1978", "150");
		Contract contract = new Contract(1, new Date(), policyHolder, "", calc, adress); 
		
		contractRepository.add(contract);
		Contract persistedContract = contractRepository.findOne(1);
		assertEquals(adress, persistedContract.getAdress());
		assertEquals(policyHolder, persistedContract.getPolicyHolder());
		assertEquals(calc, persistedContract.getCalculation());
		assert(contractRepository.findAll().contains(contract));
		assertEquals(contract, persistedContract);
	}
	
	
	@Test
	public void PersonRepositoryTest() {
		
		//personRepository.add(policyHolder);
		assertEquals(policyHolder, personRepository.findByinitials(policyHolder.getName()
				, policyHolder.getFathersName(), policyHolder.getLastName()));
	}
}
