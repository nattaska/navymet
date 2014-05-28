package com.ss.sq.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ss.sq.common.service.PasswordService;

@Component
public class SQPasswordEncoder implements PasswordEncoder {

	private PasswordService passwordService;
	
	@Override
	public String encodePassword(String rawPass, Object arg1)
			throws DataAccessException {
		return passwordService.encodePassword(rawPass);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt)
			throws DataAccessException {
		return encPass.equals(passwordService.encodePassword(rawPass));
	}
	
//	@Autowired
//	public void setPasswordService(PasswordService passwordService) {
//		this.passwordService = passwordService;
//	}
	
}
