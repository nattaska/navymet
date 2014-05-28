package com.ss.sq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ss.sq.common.entity.BaseEntityModel;

@Entity
@Table(name = "SS_USER")
public class UserModel extends BaseEntityModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3283751862583956451L;
	private String id;
	private String userName;
	private String password;
	private String isAdmin;

	
	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "ADMIN")
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

}
