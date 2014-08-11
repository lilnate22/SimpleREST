package com.nate.dao;

import java.util.List;

import com.nate.dao.model.Users;


public interface UserDAO{
	

	Users login(String user, String password);
	
	void logout(String sessionAPI);
	
	String saveToken(String username, String password, Long uid) throws Exception;
	
	boolean deleteToken(String token);
	
	List<Users> getAllUsers();
	
}