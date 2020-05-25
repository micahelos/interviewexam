package pl.eureka.credit.servicestatus;

/**
 * 
 * @param serviceID
 * @return
 */
public interface ServiceStatusChecker {
	public boolean isServiceUp(String serviceID);
	public boolean isProductAndCustomerServiceUp();
	
}
