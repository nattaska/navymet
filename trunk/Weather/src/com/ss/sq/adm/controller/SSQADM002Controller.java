package com.ss.sq.adm.controller;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fission.generator.web.controller.spring.SpringControllerMethod;
import com.fission.web.view.extjs.data.Data;
import com.fission.web.view.extjs.data.StoreData;
import com.fission.web.view.extjs.form.FormData;
import com.fission.web.view.extjs.grid.GridData;
import com.ss.sq.adm.domain.SSQADM002;
import com.ss.sq.adm.domain.SSQADM002Domain;
import com.ss.sq.resource.GenFileConstants;
import com.ss.sq.util.BeanUtils;

@Controller
public class SSQADM002Controller {
	
	@RequestMapping(method = RequestMethod.GET, value="/SSQADM002.htm")
	public ModelMap init(){
		List<StoreData> referenceStoreDatas = new ArrayList<StoreData>();
		List<Data> referenceData = new ArrayList<Data>();
		SSQADM002Domain domain = new SSQADM002Domain();
		try {
			referenceStoreDatas.add(domain.findValiableStoreData());
			referenceStoreDatas.add(domain.findStationStoreData());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data isAdmin = new Data();
		isAdmin.setVarName("isAdmin");
		
		referenceData.add(isAdmin);

		FormData formData = new FormData();
		formData.setReferenceDatas(referenceData);
		formData.setReferenceStoreDatas(referenceStoreDatas);
		return formData.createModelMap();
    }

	
	@RequestMapping(value = "/SSQADM002.htm", method = RequestMethod.POST, params = "method=search" )
	@SpringControllerMethod
	public static void findTransactionDetail(@ModelAttribute SSQADM002Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM002 ssqadm = new SSQADM002();
		String valcd = request.getParameter("valdata");
		valcd=valcd.replace(",", "','");
		StringBuffer valbuff = new StringBuffer();
		valbuff.append("'").append(valcd).append("'");
		valcd = valbuff.toString();
		String stationcd = request.getParameter("stationdata");
		stationcd=stationcd.replace(",", "','");
		StringBuffer stationbuff = new StringBuffer();
		stationbuff.append("'").append(stationcd).append("'");
		stationcd = stationbuff.toString();
		System.out.println(request.getParameter("startDate"));
		String StartDt = request.getParameter("startDate");
		String EndDt = request.getParameter("endDate");
		String StartTime = request.getParameter("startTime");
		String EndTime = request.getParameter("endTime");
		String time = request.getParameter("time");
		if(!BeanUtils.isEmpty(time)){
			time=time.replace(",", "','");
			StringBuffer timebuff = new StringBuffer();
			timebuff.append("'").append(time).append("'");
			time = timebuff.toString();
		}
		
		ssqadm.setAwsprm(valcd);
		ssqadm.setStation(stationcd);
		ssqadm.setStartDate(StartDt);
		ssqadm.setFinishDate(EndDt);
		ssqadm.setStartTime(StartTime);
		ssqadm.setEndTime(EndTime);
		ssqadm.setTime(time);
		GridData gridData = domain.findAwstnDetail(ssqadm);
		gridData.responseJson(response);
	}
	
	
	@RequestMapping(value = "/SSQADM002.htm", method = RequestMethod.POST, params = "method=genReport" )
	@SpringControllerMethod
	public static void genTransactionDetail(@ModelAttribute SSQADM002Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM002 ssqadm = new SSQADM002();
		String name = request.getSession().getAttribute("username").toString();
		String valcd = request.getParameter("valdata");
		valcd=valcd.replace(",", "','");
		StringBuffer valbuff = new StringBuffer();
		valbuff.append("'").append(valcd).append("'");
		valcd = valbuff.toString();
		String stationcd = request.getParameter("stationdata");
		stationcd=stationcd.replace(",", "','");
		StringBuffer stationbuff = new StringBuffer();
		stationbuff.append("'").append(stationcd).append("'");
		stationcd = stationbuff.toString();
		System.out.println(request.getParameter("startDate"));
		String StartDt = request.getParameter("startDate");
		String EndDt = request.getParameter("endDate");
		String StartTime = request.getParameter("startTime");
		String EndTime = request.getParameter("endTime");
		String time = request.getParameter("time");
		if(!BeanUtils.isEmpty(time)){
			time=time.replace(",", "','");
			StringBuffer timebuff = new StringBuffer();
			timebuff.append("'").append(time).append("'");
			time = timebuff.toString();
		}
		
		ssqadm.setAwsprm(valcd);
		ssqadm.setStation(stationcd);
		ssqadm.setStartDate(StartDt);
		ssqadm.setFinishDate(EndDt);
		ssqadm.setStartTime(StartTime);
		ssqadm.setEndTime(EndTime);
		ssqadm.setTime(time);
		
		String fileName = "";

		fileName = name+System.currentTimeMillis()+".csv";

			domain.genAwstnDetail(ssqadm,GenFileConstants.filepath+fileName);

			try {
			    
				response.setContentType("text/html");  
				PrintWriter out = response.getWriter();  
				response.setContentType("APPLICATION/OCTET-STREAM");   
				response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");   
				  
				FileInputStream fileInputStream = new FileInputStream(GenFileConstants.filepath + fileName);  
				            
				int i;   
				while ((i=fileInputStream.read()) != -1) {  
				out.write(i);   
				}   
				fileInputStream.close();   
				out.close();   
				
			    System.out.println("File downloaded at client successfully");

			    
			    
			    domain.updateHis(name,fileName,"1");
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}

}
