package com.ss.sq.adm.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fission.generator.web.controller.spring.SpringControllerMethod;
import com.fission.web.view.extjs.form.ActionResult;
import com.fission.web.view.extjs.grid.GridData;
import com.ss.sq.adm.domain.SSQADM001;
import com.ss.sq.adm.domain.SSQADM001Domain;
import com.ss.sq.entity.UserModel;

@Controller
public class SSQADM001Controller {

	@RequestMapping(method = RequestMethod.GET, value="/SSQADM001.htm")
	public ModelAndView init(){
		return new ModelAndView("SSQADM001");
    }
	
	@RequestMapping(method = RequestMethod.POST, value="/SSQADM001.htm")
	public void save(HttpServletRequest request,HttpServletResponse response){
		UserModel user = new UserModel();
		user.setId(request.getParameter("idTxt"));
		user.setUserName(request.getParameter("nameTxt"));
		user.setPassword(request.getParameter("passwordTxt"));
		user.setIsAdmin(request.getParameter("adminCombo"));
		SSQADM001Domain domain = new SSQADM001Domain();
		boolean isComplete = true;
		try {
			domain.saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			isComplete = false;
			e.printStackTrace();
		}
		
		ActionResult result = new ActionResult();
		result.setSuccess(isComplete);
		result.responseJson(response);
    }
	
	@RequestMapping(value = "/SSQADM001.htm", method = RequestMethod.POST, params = "method=search" )
	@SpringControllerMethod
	public static void findTransactionDetail(@ModelAttribute SSQADM001Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM001 ssqadm = new SSQADM001();

		String Id = request.getParameter("id");
		String Name = request.getParameter("name");
		String Admin = request.getParameter("admin");
		//String Password = request.getParameter("password");

		
		ssqadm.setUid(Id);
		ssqadm.setName(Name);
		ssqadm.setAdmin(Admin);

		GridData gridData = domain.findUsrDetail(ssqadm);
		gridData.responseJson(response);
	}
	
	@RequestMapping(value = "/SSQADM001.htm", method = RequestMethod.POST, params = "method=delete" )
	@SpringControllerMethod
	public static void deleteTransactionDetail(@ModelAttribute SSQADM001Domain domain,
			@RequestParam("records") String records,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(records);
		System.out.println("Method Delete Controller");
		@SuppressWarnings("unchecked")
		List<SSQADM001Domain> list = (List<SSQADM001Domain>) JSONArray.toCollection(jsonArray, SSQADM001Domain.class);

		String transaction = domain.deleteTransactionDetail(list);
		ActionResult result = new ActionResult();
		
		if(transaction!=null && " ".equals(transaction)){
			result.setSuccess(true);
		}
		else{
			System.out.println("LLOG ::::: "+transaction);
			result.setSuccess(false);
			List<String> errorMessages = new ArrayList<String>();
			errorMessages.add(transaction);
			result.setErrorMessages(errorMessages);
		}
		result.responseJson(response);
		
	}
	
	@RequestMapping(value = "/SSQADM001.htm", method = RequestMethod.POST, params = "method=modify" )
	@SpringControllerMethod
	public static void modifyTransactionDetail(@ModelAttribute SSQADM001Domain domain,
			@RequestParam("records") String records,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(records);
		System.out.println("Method Delete Controller");
		@SuppressWarnings("unchecked")
		List<SSQADM001Domain> list = (List<SSQADM001Domain>) JSONArray.toCollection(jsonArray, SSQADM001Domain.class);

		String transaction = domain.modifyTransactionDetail(list);
		ActionResult result = new ActionResult();
		
		if(transaction!=null && " ".equals(transaction)){
			result.setSuccess(true);
		}
		else{
			System.out.println("LLOG ::::: "+transaction);
			result.setSuccess(false);
			List<String> errorMessages = new ArrayList<String>();
			errorMessages.add(transaction);
			result.setErrorMessages(errorMessages);
		}
		result.responseJson(response);
		
	}
	
}
