package com.nate.utils;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.nate.rest.JavaScriptService;
import com.nate.rest.PaymentService;

public class ServerConfig extends ResourceConfig {

    /**
	* Register JAX-RS application components.
	*/	
	public ServerConfig(){
		register(RequestContextFilter.class);
		packages("com.nate.rest","com.wordnik.swagger.jaxrs.listing");
		//register(PaymentService.class);
		register(JacksonFeature.class);	
		//register(JavaScriptService.class);
	}
}

