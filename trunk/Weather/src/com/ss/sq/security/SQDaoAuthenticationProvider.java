package com.ss.sq.security;

import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

public class SQDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws UsernameNotFoundException {

		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (!StringUtils.hasText(username) || !StringUtils.hasText(password))
        {
            throw new UsernameNotFoundException("Wrong username or password");
        }
        
        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, null)) {
            throw new UsernameNotFoundException("Wrong username or password");
        }
        
		super.additionalAuthenticationChecks(userDetails, authentication);
		
	}
	
	@Override
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
		super.setUserDetailsService(this.userDetailsService);
	}
}
