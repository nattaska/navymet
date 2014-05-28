package com.ss.sq.security;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

public class SSUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3626023074201476841L;
	
	private String id;
	private String name;
	private String userGroup;
	private String module;
	
	public SSUser(
					String username, 
					String password,
					String module,
					boolean enabled,
					boolean accountNonExpired, 
					boolean credentialsNonExpired,
					boolean accountNonLocked, 
					GrantedAuthority[] authorities,
					String name,
					String userGroup)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,accountNonLocked, authorities);
		this.name=name;
		this.userGroup=userGroup;
		this.module=module;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}


	public String getModule() {
		return module;
	}


	public void setModule(String module) {
		this.module = module;
	}





}
