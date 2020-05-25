package pl.eureka.customer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pl.eureka.customer.data.Customer;

@RestController
@RequestMapping(path="/", produces="application/json")
public class CustomerController {

	@Autowired
	private CustomerService service;

	public CustomerController(CustomerService service) {
		this.service=service;
	}

	@GetMapping("GetCustomers/{id}")
	public Customer get (@PathVariable(name ="id") Long id) {
		try {
			return service.getCustomerById(id);
		}
		catch(RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found", e);
		}
	}
	
	@PostMapping("CreateCustomer")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer post(@RequestBody Customer customer) {
		return service.createCustomer(customer);
	}
	
	@GetMapping("GetCustomers")
	public List<Customer> get () {
		try {
			return service.getAll();
		}
		catch(RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found", e);
		}
	}
	
}
