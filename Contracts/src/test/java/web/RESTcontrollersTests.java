package web;

import static org.junit.Assert.assertEquals;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import contracts.SpringBootWebApplication;
import contracts.domain.Adress;
import contracts.domain.Calculation;
import contracts.domain.Contract;
import contracts.domain.DefCalculationImpl;
import contracts.domain.EstateType;
import contracts.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootWebApplication.class)
@AutoConfigureMockMvc
public class RESTcontrollersTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	Person policyHolder = new Person("John", "Doe", "", new Date(), 1, 1);
	Adress adress = new Adress("USA", "111222", "New York city", "", "", "Elm str", 34, "", "", 66);
	EstateType propertyType = new EstateType("Квартира", 1.7f);
	Calculation calc = new DefCalculationImpl(new Date(), new Date(), propertyType, "1978", "150");
	Contract contract = new Contract(2, new Date(), policyHolder, "", calc, adress); 
		
	@Test
	public void shouldSaveAndGetPerson() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		String JSONperson = mapper.writeValueAsString(policyHolder);
		mapper.registerModule(new JavaTimeModule());
		System.out.println(JSONperson);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/persons/add")
				.content(JSONperson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		
		//perform get		
		requestBuilder = MockMvcRequestBuilders
				.get("/persons/getone")
				.param("name", policyHolder.getName())
				.param("fathersName", policyHolder.getFathersName())
				.param("lastName", policyHolder.getLastName())
				.accept(MediaType.APPLICATION_JSON);

		result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		
		assert(!result.getResponse().getContentAsString().isEmpty());
		
		JSONAssert.assertEquals(JSONperson, result.getResponse()
				.getContentAsString(), new CustomComparator(JSONCompareMode.LENIENT,
		                new Customization("id", (o1, o2) -> true)));
		
	}
	
	@Test
	public void shouldSaveAndGetContract() throws Exception {
						
		ObjectMapper mapper = new ObjectMapper();
		String JSONcontract = mapper.writeValueAsString(contract);
		System.out.println(JSONcontract);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/contracts/add")
				.content(JSONcontract)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
		
		//perform get		
		requestBuilder = MockMvcRequestBuilders.get(
				"/contracts/2").accept(
				MediaType.APPLICATION_JSON);

		result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		
		assert(!result.getResponse().getContentAsString().isEmpty());
		
		JSONAssert.assertEquals(JSONcontract, result.getResponse()
				.getContentAsString(), new CustomComparator(JSONCompareMode.LENIENT,
		                new Customization("*.id", (o1, o2) -> true)));

	}
		
	
}
