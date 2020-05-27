package pl.eureka.credit.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.eureka.credit.exception.CreditServiceException;
import pl.eureka.credit.model.CreditCreateModel;
import pl.eureka.credit.model.CreditInformationModel;

@RestController
@RequestMapping(path="/", produces="application/json")
public class CreditController  {

	@Autowired
	private CreditService service; 
	
	public CreditController(CreditService service) {
		this.service=service;
	}

	@GetMapping("GetCredits")
	@ResponseBody
	public List<CreditInformationModel> getCredits() throws CreditServiceException {
		return service.getCredits();
	}

	@PostMapping(path="CreateCredit")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Long> post(@RequestBody CreditCreateModel credit) throws CreditServiceException {
		return service.createCredit(credit);
	}
}
