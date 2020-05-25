package pl.eureka.credit.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface CreditRepository extends JpaRepository<Credit, Long> {
	List<Credit> findAll();
}
