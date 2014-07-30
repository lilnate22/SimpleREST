package com.nate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nate.dao.model.Items;

@Repository
public class ItemDAOImpl implements ItemDAO {
	
	private static Logger log = LoggerFactory.getLogger(ItemDAOImpl.class);

	private EntityManager entityManager;
	
	
	 public EntityManager getEntityManager() {
	        return entityManager;
	    }
	 
	  @PersistenceContext
	  public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	   }
	
	@Transactional
	public void AddItem(Items item) {
		log.info("Adding Item "+item.getName()+" to database");
		this.entityManager.persist(item);
	}

	@Transactional
	public List<Items> getAllItemsFomUser(double userID) {
		log.info("Selecting item for user: "+userID);
		String qlstring = "Select p from Items p WHERE userID=:id";
		TypedQuery<Items> items = this.entityManager.createQuery(qlstring, Items.class);
		items.setParameter("id", userID);
		return items.getResultList();
	}

	@Override
	public boolean editItem(Items item, long itemID) {
		log.info("Editing item for itemID: " +itemID+" with userid: "+item.getUserID());
		return false;
	}

}
