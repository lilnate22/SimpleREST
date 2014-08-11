package com.nate.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import org.codehaus.jackson.map.annotate.JsonRootName;

@Entity
@Table(name="Tokens")
@JsonRootName(value="Tokens")
public class Tokens {
	
	@Column(name="Id")
	@Id
	private long Id;
	
	@Column(name="userID")
	private double userID;
	
	@Column(name="token")
	private String token;
	@Column(name="expire")
	private String expire;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public double getUserID() {
		return userID;
	}
	public void setUserID(double userID) {
		this.userID = userID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}

}
