package com.ss.sq.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fission.generator.web.controller.spring.SpringControllerMethod;

@Controller
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET, value="/login.htm")
	public ModelAndView init(){
		return new ModelAndView("login");
    }
	
	@SpringControllerMethod
	@RequestMapping(
			method = {RequestMethod.GET, RequestMethod.POST} 
			, value = "/accessDenied.htm"
	)
	public ModelAndView accessDeniedInit() {
		return new ModelAndView("accessDenied");
	}
}
