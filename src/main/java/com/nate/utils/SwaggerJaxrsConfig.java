package com.nate.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.wordnik.swagger.jaxrs.config.BeanConfig;


@WebListener
public class SwaggerJaxrsConfig implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setResourcePackage("com.nate.rest");
		beanConfig.setBasePath("http://localhost:8080/SimpleREST/resources/");
		beanConfig.setDescription("API resources");
		beanConfig.setTitle("REST API");
		beanConfig.setScan(true);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
