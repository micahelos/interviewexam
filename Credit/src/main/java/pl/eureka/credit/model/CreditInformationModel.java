package pl.eureka.credit.model;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Setter;
import pl.eureka.credit.data.Credit;

@Setter
@AllArgsConstructor
public class CreditInformationModel {

	private Customer customer;
	private Product product;
	private Credit credit;
	
	public Map<String,String> getCredit() {
		Map<String, String> credit = new LinkedHashMap<>();
		credit.put("creditName", this.credit.getCreditName());
		return credit;
	}
	public Map<String,String> getProduct() {
		Map<String, String> products = new LinkedHashMap<>();
		products.put("ProductName",product.getProductName());
		products.put("Value",String.valueOf(product.getValue()));
		return products;
	}
	public Map<String,String> getCustomer() {
		Map<String, String> client = new LinkedHashMap<>();
		client.put("FirstName", customer.getFirstName());
		client.put("Surname", customer.getSurname());
		client.put("Pesel", customer.getPesel());
		return client;
	}	
	
}
