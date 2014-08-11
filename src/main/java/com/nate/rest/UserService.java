package com.nate.rest;


import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nate.dao.UserDAO;
import com.nate.dao.model.Users;

@Component
@Path("/user")
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	UserDAO userdao;
	
	@GET
	@Path("/login/{username}/{password}")
	public Response login(@PathParam("username") String user, @PathParam("password") String password, @CookieParam(value="LoginCookie") String logincook)
	{
		try{
			Cookie lc; //login cookie
			Cookie tc; //token cookie
			Users login = userdao.login(user, password);
			
			if(login != null){
				String token = userdao.saveToken(user, password, 1L);
				lc = new Cookie("LoginCookie",user,"/",null);
				tc = new Cookie("TokenCookie",token,"/",null);
			}
			else{
				lc = new Cookie("LoginCookie",null,"/",null);
				tc = new Cookie("TokenCookie",null,"/",null);
			}
			
			NewCookie LogginCookie = new NewCookie(lc,"This cookie is for login details",36000,false);
			NewCookie TokenCookie = new NewCookie(tc,"this is for the token",36000,false);
			
			
			
			String s = "You have been infected by cookie monster! "+logincook;
			
			return Response.status(200).entity(s).cookie(LogginCookie).cookie(TokenCookie).build();
			
		}catch(Exception e)
		{
			log.error(e.getMessage());
			return Response.status(500).build();
		}
	}
	
	@GET
	@Path("/newCookie")
	public Response checkCookie(@CookieParam(value="LoginCookie") String LoginCookie )
	{
		String s = "hello "+LoginCookie;
		
		return Response.status(200).entity(s).build();
	}

}
