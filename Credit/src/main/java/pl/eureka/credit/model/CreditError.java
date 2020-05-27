package pl.eureka.credit.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force=true)
public class CreditError {

	private int errorCode;
	private String errorMessage;
	
	public CreditError(int errorCode, String errorMessage) {
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
}
