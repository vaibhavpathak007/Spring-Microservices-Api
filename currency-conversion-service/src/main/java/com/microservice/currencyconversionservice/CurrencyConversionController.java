package com.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.currencyconversionservice.modal.CurrencyConversionBean;
import com.microservice.currencyconversionservice.service.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private RestTemplate templet;
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

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
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean currencyConversionBean = proxy.retriveExchangeValue(from, to);
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setTotalCalculateAmount(currencyConversionBean.getQuantity().multiply(currencyConversionBean.getConversionMultiple()));
		logger.info("{}",currencyConversionBean);
		return currencyConversionBean;
	}
	
}
