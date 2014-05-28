package com.ss.sq.adm.domain;

import com.fission.web.view.extjs.grid.PagingGridCriteria;

public class SSQADM001 extends PagingGridCriteria {
	private String uid;
	private String name;
	private String admin;
	private String password;

	

	public String getUid() {
		return uid;
	}




	public void setUid(String uid) {
		this.uid = uid;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAdmin() {
		return admin;
	}




	public void setAdmin(String admin) {
		this.admin = admin;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		System.out.println("SSQADM001 [uid=" + uid + ", name=" + name
				+ ", admin=" + admin + ", password=" + password
				+ "]");
		return "SSQADM001 [id=" + uid + ", name=" + name
				+ ", admin=" + admin + ", password=" + password
				+ "]";
	}
}
