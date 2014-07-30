package com.nate.rest;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Path("/javascript")
public class JavaScriptService {
		
	
	@GET
	@Path("/{js}")
	@Transactional
	public String getJavaScript(@PathParam(value = "js") String jsapp)
	{
		return "function myFunction() { alert(\"Hello! I am an alert box!\");}";
	}
	
}
