package com.ss.sq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fission.web.view.extjs.form.ActionResult;
import com.ss.sq.common.JsonHttpResponderConfig;
import com.ss.sq.common.entity.MenuDto;


@Controller
@RequestMapping("/menu.htm")
public class MenuController {
	
	@RequestMapping(method = RequestMethod.POST ,params = {"method=getRole"})
	public void init(HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getSession().getAttribute("username"));
		System.out.println(request.getSession().getAttribute("admin"));

		List<MenuDto> menuLs= new ArrayList<MenuDto>();
		List<MenuDto> userMenuLs= new ArrayList<MenuDto>();
		
		MenuDto addUser = new MenuDto("1","User Maintenance",request.getContextPath()+"/SSQADM001.htm");
		MenuDto addLog = new MenuDto("2","ข้อมูลผลการตรวจอากาศอัตโนมัติ",request.getContextPath()+"/SSQADM002.htm");	
		MenuDto transaction = new MenuDto("3","ข้อมูลผลการตรวจอากาศของสัตหีบ",request.getContextPath()+"/SSQADM003.htm");
		MenuDto report1= new MenuDto("4","ข้อมูลผลการตรวจอากาศจากสถานีทั่วโลก",request.getContextPath()+"/SSQADM004.htm");
		MenuDto report2= new MenuDto("5","ข้อมูลผลการจำลองคลื่น",request.getContextPath()+"/SSQADM005.htm");

		MenuDto logout = new MenuDto("8","Log out",request.getContextPath()+"/login.htm");

		MenuDto userMenu = new MenuDto("1",request.getSession().getAttribute("username").toString(),"");

		if("Y".equals(request.getSession().getAttribute("admin").toString())){
			menuLs.add(addUser);
		}
		menuLs.add(addLog);
		menuLs.add(transaction);
		menuLs.add(report1);
		menuLs.add(report2);
		menuLs.add(logout);
		
		userMenuLs.add(userMenu);

		JSONObject jsonObject = JSONObject.fromObject(new Object(), JsonHttpResponderConfig.getJsonConfig());
		jsonObject.accumulate("menu", menuLs, JsonHttpResponderConfig.getJsonConfig());
		jsonObject.accumulate("userMenu", userMenuLs, JsonHttpResponderConfig.getJsonConfig());
		ActionResult result = new ActionResult();
		result.setSuccess(true);
		result.responseJson(response, jsonObject);
    }
}
