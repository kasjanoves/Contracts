package contracts.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import contracts.data.EstateTypesRepository;
import contracts.domain.EstateType;

@RestController
@RequestMapping("/estatetypes")
@CrossOrigin(origins = "http://127.0.0.1:8888")
public class EstateTypesRESTController {

	@Autowired
	EstateTypesRepository repository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<EstateType> estateTypes(){
		return repository.findAll();
	}
	
	@RequestMapping(value="/getone", method=RequestMethod.GET)
	public EstateType getOne(String type) {
		return repository.getOne(type);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(@RequestBody @Valid EstateType estateType) {
		repository.save(estateType);
	}
}
