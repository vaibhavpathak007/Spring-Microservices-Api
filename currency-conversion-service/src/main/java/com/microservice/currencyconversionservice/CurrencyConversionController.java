package com.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.currencyconversionservice.modal.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	RestTemplate templet;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		CurrencyConversionBean currencyConversionBean = templet.getForObject("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setTotalCalculateAmount(currencyConversionBean.getQuantity().multiply(currencyConversionBean.getConversionMultiple()));
		
		return currencyConversionBean;
	}
	
}
