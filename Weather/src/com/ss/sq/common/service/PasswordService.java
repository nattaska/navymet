package com.ss.sq.common.service;

public interface PasswordService {
	String encodePassword(final String password);
	String passwordVerified(final Integer userId,final String password);
}
