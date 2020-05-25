package pl.eureka.credit.servicestatus;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.discovery.EurekaClient;

import lombok.Data;

@Data
@Component
/**
 * 
 * @author Michal
 *	Zwraca informacje o statusie serwisow w serwerze Eureka
 */
public class ServiceReadyCheck implements ServiceStatusChecker{

	@Value("${product.urls.serviceID}")
	private String productServiceID;

	@Value("${customer.urls.serviceID}")
	private String customerServiceID;

	@Autowired
	private EurekaClient discoveryClient;

	@Override
	public boolean isServiceUp(String serviceID) {
		return Optional.ofNullable(discoveryClient.getApplication(serviceID)).isPresent();
	}

	@Override
	public boolean isProductAndCustomerServiceUp() {
		if(discoveryClient.getApplication(productServiceID) != null && discoveryClient.getApplication(customerServiceID)!=null )
			return true;
		else
			return false;
	}
}