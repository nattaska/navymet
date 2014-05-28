package com.ss.sq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ss.sq.adm.domain.SSQADM001Domain;
import com.ss.sq.entity.UserModel;


@Controller
@RequestMapping("/main.htm")
public class MainController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelMap init(){
		ModelMap modelMap = new ModelMap();
		return modelMap;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response){
		UserModel user = new UserModel();
		user.setId(request.getParameter("username"));
		user.setPassword(request.getParameter("j_password"));
		SSQADM001Domain domain = new SSQADM001Domain();
		boolean isComplete = false;
		boolean isAdmin = false;
		try {
			isComplete = domain.loginValidate(user);
			isAdmin = domain.isAdmin(user);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("login");
		}
		
		if(isComplete){
			HttpSession s = request.getSession();
			s.setAttribute("username", user.getId());
			if(isAdmin){
				s.setAttribute("admin", 'Y');
			}
			else{
				s.setAttribute("admin", 'N');
			}
			return new ModelAndView("main");			
		}
		else{
			return new ModelAndView("login");
		}
		
    }
}
