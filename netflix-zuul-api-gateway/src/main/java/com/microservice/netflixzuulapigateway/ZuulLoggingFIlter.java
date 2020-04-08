package com.microservice.netflixzuulapigateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFIlter extends ZuulFilter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	// TO ensure filter will execute or not
	@Override
	public boolean shouldFilter() {
		return  true;
	}

	// Interception Logic
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("Request -> {} Request URI -> {}",request, request.getRequestURI());
		return null;
	}
	
	// When should execute the filter after request - on 'pre', 'post' or 'error'
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1; // Setting Filter Priority of filter.
	}

}
