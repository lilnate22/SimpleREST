package com.nate.dao;

import java.util.List;

import com.nate.dao.model.Users;


public interface UserDAO{
	
	void Save(Users user);
	void Delete();
	void Update();
	
	List<Users> getAllUsers();
	
}