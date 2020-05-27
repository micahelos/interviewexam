package pl.eureka.credit.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pl.eureka.credit.exception.CreditServiceException;
import pl.eureka.credit.model.CreditError;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(CreditServiceException.class)
	public ResponseEntity<CreditError> mapException(CreditServiceException ex){
		CreditError error = new CreditError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<CreditError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
