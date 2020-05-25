package pl.eureka.credit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.eureka.credit.data.Credit;

@Data
@AllArgsConstructor
public class CreditCreateModel {

	private Customer customer;
	private Product product;
	private Credit credit;
	
}
