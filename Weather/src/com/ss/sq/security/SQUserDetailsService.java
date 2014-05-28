package com.ss.sq.security;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.fission.persistence.dao.GenericFinderDao;
import com.ss.sq.entity.UserModel;

public class SQUserDetailsService implements UserDetailsService {
	
	private GenericFinderDao genericFinderDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String[] str=username.split("#");
		Integer userId=Integer.parseInt(str[0]);
		DetachedCriteria criteria = DetachedCriteria.forClass(UserModel.class , "user");
		criteria.add(Restrictions.eq("user.id", userId));
		UserModel user=genericFinderDao.findUniqueByCriteria(criteria);

		if(user!= null ){
			throw new UsernameNotFoundException("Wrong username or password");
		}
		throw new UsernameNotFoundException("Wrong username or password");
	}	
	
	@Autowired
	public void setGenericFinderDao(GenericFinderDao genericFinderDao) {
		this.genericFinderDao = genericFinderDao;
	}


}
