package com.microservice.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration cfg;
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromConfiguration() {
		return new LimitConfiguration(cfg.getMaximum(), cfg.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod = "fallbackHandler")
	public LimitConfiguration retriveConfiguration() {
		throw new RuntimeException("Not Available");
	}
	
	public LimitConfiguration fallbackHandler() {
		return new LimitConfiguration(9, 999);
	}
	
	
}
