package com.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration cfg;
	
	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromConfiguration() {
		return new LimitConfiguration(cfg.getMaximum(), cfg.getMinimum());
	}
	
}
