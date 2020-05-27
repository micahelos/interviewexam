package pl.eureka.credit.api;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.eureka.credit.data.CreditRepository;
import pl.eureka.credit.exception.CreditServiceException;
import pl.eureka.credit.model.CreditCreateModel;
import pl.eureka.credit.model.CreditInformationModel;
import pl.eureka.credit.model.Customer;
import pl.eureka.credit.model.Product;
import pl.eureka.credit.servicestatus.ServiceStatusChecker;

@Service
public class CreditService {

	@Autowired
	private CreditRepository repo;
	
	@Autowired
	private ServiceStatusChecker serviceStatusChecker;
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${customer.urls.CreateCustomer}")
	private String createCustomerURL;
	@Value("${customer.urls.GetCustomers}")
	private String getCustomersURL;
	@Value("${product.urls.GetProducts}")
	private String getProductsURL;
	@Value("${product.urls.CreateProduct}")
	private String createProductURL;
	@Value("${eureka.client.service-url.default-zone}")
	private String eurekaServerURL;

	public List<CreditInformationModel> getCredits() throws CreditServiceException {
		try {
			List<Product> productList = getProducts();
			List<Customer> customerList = getCustomers();
			return repo.findAll().stream().map(credit -> {
				Product product = findProductByCreditId(productList,credit.getId());
				Customer customer = findCustomerByCreditId(customerList,credit.getId());
				return new CreditInformationModel(customer, product, credit);
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw new CreditServiceException("Can't execute GetCredits service. " + e.getMessage().toString());
		}
	}

	@Transactional(rollbackOn={RuntimeException.class,IllegalStateException.class, ConnectException.class, CreditServiceException.class})
	public Map<String,Long> createCredit(CreditCreateModel newCreditModel) throws CreditServiceException {

		try {
			newCreditModel.setCredit(Optional.ofNullable(repo.save(newCreditModel.getCredit())).orElseThrow(RuntimeException::new)); 
			newCreditModel.getCustomer().setId(newCreditModel.getCredit().getId());
			newCreditModel.getProduct().setId(newCreditModel.getCredit().getId());

			createCustomer(newCreditModel);
			createProduct(newCreditModel);

			Map<String, Long> response = new HashMap<String, Long>();
			response.put("creditID",newCreditModel.getCredit().getId());
			
			return response;
		} catch(Exception e) {
			throw new CreditServiceException("Problem coused by one of endpoints. Changes in creditdb are rollback. ");
		}
	}

	private void createCustomer(CreditCreateModel newCreditModel) throws ConnectException {
		if(serviceStatusChecker.isProductAndCustomerServiceUp())
			restTemplate.postForEntity(createCustomerURL, newCreditModel.getCustomer(), String.class);
		else
			throw new ConnectException();
	
	}
	
	private void createProduct(CreditCreateModel newCreditModel) throws ConnectException {
		if(serviceStatusChecker.isProductAndCustomerServiceUp())
			restTemplate.postForObject(createProductURL, newCreditModel.getProduct(), Product.class);
		else 
			throw new ConnectException();
	}
	
	private Product findProductByCreditId(List<Product> products, Long id){
		return products.stream().filter(e->e.getId().equals(id)).findFirst().get();
	}

	private Customer findCustomerByCreditId(List<Customer> customers, Long id){
		return customers.stream().filter(e->e.getId().equals(id)).findFirst().get();
	}

	private List<Product> getProducts() {
		return restTemplate.exchange(getProductsURL, HttpMethod.GET,null,new ParameterizedTypeReference<List<Product>>() {}).getBody();
	}
	
	private List<Customer> getCustomers() {
		return restTemplate.exchange(getCustomersURL, HttpMethod.GET,null,new ParameterizedTypeReference<List<Customer>>() {}).getBody();
	}
}
