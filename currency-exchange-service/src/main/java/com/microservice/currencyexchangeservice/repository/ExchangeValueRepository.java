package com.microservice.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.currencyexchangeservice.modal.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
	
	ExchangeValue findByFromAndTo(String from, String to);
	
}
