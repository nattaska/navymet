package com.ss.sq.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.SpringSecurityFilter;

import com.fission.persistence.service.GenericEntityService;

public class MenuAccessFilter extends SpringSecurityFilter  {
	
	@Override
	public int getOrder() {
		return FilterChainOrder.LOGOUT_FILTER+1;
	}

	@Override
	protected void doFilterHttp(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uri = httpRequest.getRequestURI();
		int slashIndex = uri.lastIndexOf('/');
		int pointIndex = uri.lastIndexOf('.');
		if (slashIndex > pointIndex)
		{
			pointIndex = uri.length();
		}
		
		if (pointIndex >= 0) 
		{
		}

		chain.doFilter(request, response);
	}

	@Autowired
	public void setGenericEntityService(GenericEntityService genericEntityService) {
	}

}
