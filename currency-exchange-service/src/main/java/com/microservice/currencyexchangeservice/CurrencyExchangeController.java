package com.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.currencyexchangeservice.modal.ExchangeValue;
import com.microservice.currencyexchangeservice.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	Environment environment;
	
	@Autowired
	ExchangeValueRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue ev = repository.findByFromAndTo(from, to);
		ev.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{}", ev);
		return ev;
	}

}
