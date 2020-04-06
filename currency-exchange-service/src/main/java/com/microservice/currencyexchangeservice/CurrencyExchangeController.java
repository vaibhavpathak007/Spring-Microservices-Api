package com.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.currencyexchangeservice.modal.ExchangeValue;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	Environment environment;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue ev = new ExchangeValue(1,from,to,BigDecimal.valueOf(65));
		ev.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return ev;
	}

}
