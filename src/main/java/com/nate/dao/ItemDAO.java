package com.nate.dao;

import java.util.List;

import com.nate.dao.model.Items;

public interface ItemDAO {
	
	void AddItem(Items item);
	List<Items> getAllItemsFomUser(double userID);
	boolean editItem(Items item, long itemID);
	

}
