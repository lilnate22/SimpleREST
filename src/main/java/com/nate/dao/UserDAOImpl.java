package com.nate.dao;

import java.util.List;







import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nate.dao.model.Users;


@Repository
public class UserDAOImpl implements UserDAO {

	 
	 private EntityManager entityManager;
	
	
	 public EntityManager getEntityManager() {
	        return entityManager;
	    }
	    @PersistenceContext
	    public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
	    
	@Override
	 @Transactional
	public void Save(Users user) {
		// TODO Auto-generated method stub

	}

	@Override
	 @Transactional
	public void Delete() {
		// TODO Auto-generated method stub

	}

	@Override
	 @Transactional
	public void Update() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Users> getAllUsers() {

		String qlString = "SELECT p FROM Users p";
	    TypedQuery<Users> query = entityManager.createQuery(qlString, Users.class);        
	 
	    return query.getResultList();
	}

}
