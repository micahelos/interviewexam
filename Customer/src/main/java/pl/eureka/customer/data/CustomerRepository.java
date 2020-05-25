package pl.eureka.customer.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
