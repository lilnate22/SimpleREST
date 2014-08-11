package com.nate.dao;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;























import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nate.dao.model.Tokens;
import com.nate.dao.model.Users;


@Repository
public class UserDAOImpl implements UserDAO {

	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

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
	public List<Users> getAllUsers() {

		try{
			String qlString = "SELECT p FROM Users p";
		    TypedQuery<Users> query = entityManager.createQuery(qlString, Users.class);        
		    return query.getResultList();
		}catch(Exception e)
		{
			log.error(e.getMessage());
			return null;
		}
	}
	@Override
	public Users login(String user, String password) {
		try{
			String qlString = "Select p from Users p WHERE p.username =:un AND p.password=:pwd";
			TypedQuery<Users> query = this.entityManager.createQuery(qlString,Users.class).setParameter("un", user)
					.setParameter("pwd", password);
			
			Users dbUser = query.getSingleResult();
			if(dbUser != null) //user details exist
				return dbUser;
			
			log.debug("Attempted Login: User="+user+" Pass: "+password);
			return null;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Exception : "+e.getStackTrace());
			return null;
		}
	}
	public void logout(String sessionAPI) {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	public String saveToken(String username, String password, Long uid) throws Exception {
		
		try{
			String salt = "alphabet";
			String message = username+password+salt+uid.toString();
			String token = DigestUtils.md5Hex(message);
			Tokens dbToken = new Tokens();
			dbToken.setToken(token);
			dbToken.setUserID(uid);
			
			Timestamp date = new Timestamp(new Date().getTime());
			dbToken.setExpire(date.toString());
			
			
			//delete any old tokens we may have
			log.info("Attempting to delete any tokens from user: "+uid);
			
			Tokens delToken = this.entityManager.find(Tokens.class, uid);
			//this.entityManager.getTransaction().begin();
			this.entityManager.remove(delToken);
			//this.entityManager.getTransaction().commit();
			
			
			//now we can save the new token to the db
			log.info("Saving Token to DB");
			
			this.entityManager.persist(dbToken);
			
			return token;
			
		}catch(Exception e)
		{
			log.error("Cannot save Token: "+e.getStackTrace());
			e.printStackTrace();
			throw new Exception(e);
		}

	}
	@Override
	public boolean deleteToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
