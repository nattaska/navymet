package com.ss.sq.security;

import org.springframework.security.providers.encoding.PasswordEncoder;

public class PasswordEncryption {
	private static PasswordEncoder passwordEncoder;
	
	public static void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        PasswordEncryption.passwordEncoder = passwordEncoder;
    }
    
    public static String encrypt(String plainText)
    {
        return passwordEncoder.encodePassword(plainText, null);
    }
}
