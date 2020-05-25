package pl.eureka.customer.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.eureka.customer.data.Customer;
import pl.eureka.customer.data.CustomerRepository;

@Service
public class CustomerService{

	@Autowired
	private CustomerRepository repo;
	
	public CustomerService(CustomerRepository repo) {
		this.repo= repo;
	}
	
	public Customer createCustomer(Customer customer) {
		return Optional.ofNullable(repo.save(customer)).orElseThrow(RuntimeException::new);
	}
	
	public Customer getCustomerById(Long id) {
		return repo.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public List<Customer> getAll() {
		return repo.findAll();
	}
	

}

