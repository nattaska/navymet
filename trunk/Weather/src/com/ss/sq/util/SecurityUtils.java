package com.ss.sq.util;

import org.springframework.security.context.SecurityContextHolder;

import com.ss.sq.security.SSUser;

public class SecurityUtils {
	
	public static SSUser getUser() {
		SSUser mwUser = null;
		SecurityContextHolder securityContextHolder = new SecurityContextHolder();
		if (BeanUtils.isNotNull(securityContextHolder.getContext().getAuthentication()) && 
				securityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SSUser) {
			mwUser = (SSUser) securityContextHolder.getContext().getAuthentication().getPrincipal();
		} 
		System.out.println("mwUser>>>"+mwUser.getUsername());
		return mwUser;
	}
	
	public static boolean isHasUserInSession() {
		boolean isAlreadyHasUser = false;
		if (BeanUtils.isNotNull(getUser())) {
			isAlreadyHasUser = true;
		}
		return isAlreadyHasUser;
	}
	
}
