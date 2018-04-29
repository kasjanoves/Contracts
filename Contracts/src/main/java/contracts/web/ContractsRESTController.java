package contracts.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import contracts.data.ContractRepository;
import contracts.domain.Calculation;
import contracts.domain.Contract;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8888")
@RequestMapping("/contracts")
public class ContractsRESTController {

	@Autowired
	ContractRepository repository;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello() {
		return "Hello client!";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Contract> contracts(){
		return repository.findAll();
	}
	
	@RequestMapping(value="/{num}", method=RequestMethod.GET,
			produces="application/json")
	public ResponseEntity<Contract> getOne(@PathVariable long num) {
		Contract contract = repository.findOne(num);
		HttpStatus status = contract != null ?
				HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Contract>(contract, status);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST,
			consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Contract> add(@RequestBody @Valid Contract contract) {
		Contract persistedContract =  repository.add(contract);
		return new ResponseEntity<Contract>(persistedContract, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST,
			consumes="application/json")
	public void save(@RequestBody @Valid Contract contract) {
		repository.save(contract);
	}
	
	@RequestMapping(value="/calculate", method=RequestMethod.POST)
	public ResponseEntity<Calculation> calculate (@RequestBody @Valid Calculation calc) {
		try {
			calc.calculate();
		} catch (Exception e) {
			return new ResponseEntity<Calculation>(calc, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Calculation>(calc, HttpStatus.OK);
	}
	
}
