package com.microservice.limitsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromConfiguration() {
		
		return new LimitConfiguration(1000, 1);
		
	}
	
}
