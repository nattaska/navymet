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
import com.ss.sq.adm.domain.SSQADM003;
import com.ss.sq.adm.domain.SSQADM003Domain;
import com.ss.sq.resource.GenFileConstants;
import com.ss.sq.util.BeanUtils;

@Controller
public class SSQADM003Controller {
	
	@RequestMapping(method = RequestMethod.GET, value="/SSQADM003.htm")
	public ModelMap init(){
		List<StoreData> referenceStoreDatas = new ArrayList<StoreData>();
		List<Data> referenceData = new ArrayList<Data>();
		SSQADM003Domain domain = new SSQADM003Domain();
		try {
			referenceStoreDatas.add(domain.findValiableStoreData());
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

	
	@RequestMapping(value = "/SSQADM003.htm", method = RequestMethod.POST, params = "method=search" )
	@SpringControllerMethod
	public static void findTransactionDetail(@ModelAttribute SSQADM003Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM003 ssqadm = new SSQADM003();
		String valcd = request.getParameter("valdata");
		valcd=valcd.replace(",", "','");
		StringBuffer valbuff = new StringBuffer();
		valbuff.append("'").append(valcd).append("'");
		valcd = valbuff.toString();
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
		
		String ulatitude = request.getParameter("uLattitudeTxt");
		String calulatitude = "";
		String calllatitude = "";
		String calllongtitude = "";
		String calrlongtitude = "";
		if(!BeanUtils.isEmpty(ulatitude)){
			if("N".equals(ulatitude.substring(ulatitude.length() - 1))){
				ulatitude = ulatitude.substring(0,ulatitude.length()-1);
				String[] ulatitudeData = ulatitude.split("\\.");
				Double X = Double.valueOf(ulatitudeData[0].trim());
				Double Y = Double.valueOf(ulatitudeData[1].trim());
				calulatitude = String.valueOf(X+(Y/60));
			}
			else{
				ulatitude = ulatitude.substring(0,ulatitude.length()-1);
				String[] ulatitudeData = ulatitude.split("\\.");
				Double A = Double.valueOf(ulatitudeData[0].trim());
				Double B = Double.valueOf(ulatitudeData[1].trim());
				calulatitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		String llatitude = request.getParameter("lLattitudeTxt");
		if(!BeanUtils.isEmpty(llatitude)){
			if("N".equals(llatitude.substring(llatitude.length() - 1))){
				llatitude = llatitude.substring(0,llatitude.length()-1);
				String[] llatitudeData = llatitude.split("\\.");
				Double X = Double.valueOf(llatitudeData[0].trim());
				Double Y = Double.valueOf(llatitudeData[1].trim());
				calllatitude = String.valueOf(X+(Y/60));
			}
			else{
				llatitude = llatitude.substring(0,llatitude.length()-1);
				String[] llatitudeData = llatitude.split("\\.");
				Double A = Double.valueOf(llatitudeData[0].trim());
				Double B = Double.valueOf(llatitudeData[1].trim());
				calllatitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		String llongtitude = request.getParameter("lLontitudeTxt");
		if(!BeanUtils.isEmpty(llongtitude)){
			if("E".equals(llongtitude.substring(llongtitude.length() - 1))){
				llongtitude = llongtitude.substring(0,llongtitude.length()-1);
				String[] llongtitudeData = llongtitude.split("\\.");
				Double X = Double.valueOf(llongtitudeData[0].trim());
				Double Y = Double.valueOf(llongtitudeData[1].trim());
				calllongtitude = String.valueOf(X+(Y/60));
			}
			else{
				llongtitude = llongtitude.substring(0,llongtitude.length()-1);
				String[] llongtitudeData = llongtitude.split("\\.");
				Double A = Double.valueOf(llongtitudeData[0].trim());
				Double B = Double.valueOf(llongtitudeData[1].trim());
				calllongtitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		String rlongtitude = request.getParameter("rLontitudeTxt");
		if(!BeanUtils.isEmpty(rlongtitude)){
			if("E".equals(rlongtitude.substring(rlongtitude.length() - 1))){
				rlongtitude = rlongtitude.substring(0,rlongtitude.length()-1);
				String[] rlongtitudeData = rlongtitude.split("\\.");
				Double X = Double.valueOf(rlongtitudeData[0].trim());
				Double Y = Double.valueOf(rlongtitudeData[1].trim());
				calrlongtitude = String.valueOf(X+(Y/60));
			}
			else{
				rlongtitude = rlongtitude.substring(0,rlongtitude.length()-1);
				String[] rlongtitudeData = rlongtitude.split("\\.");
				Double A = Double.valueOf(rlongtitudeData[0].trim());
				Double B = Double.valueOf(rlongtitudeData[1].trim());
				calrlongtitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		ssqadm.setSwsval(valcd);
		ssqadm.setStartDate(StartDt);
		ssqadm.setFinishDate(EndDt);
		ssqadm.setStartTime(StartTime);
		ssqadm.setEndTime(EndTime);
		ssqadm.setTime(time);
		ssqadm.setUpLatitide(calulatitude);
		ssqadm.setLowLatitide(calllatitude);
		ssqadm.setLeftLongtitude(calllongtitude);
		ssqadm.setRightLongtitude(calrlongtitude);
		GridData gridData = domain.findSwstnDetail(ssqadm);
		gridData.responseJson(response);
	}
	
	@RequestMapping(value = "/SSQADM003.htm", method = RequestMethod.POST, params = "method=genReport" )
	@SpringControllerMethod
	public static void genTransactionDetail(@ModelAttribute SSQADM003Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM003 ssqadm = new SSQADM003();
		String name = request.getSession().getAttribute("username").toString();
		String valcd = request.getParameter("valdata");
		valcd=valcd.replace(",", "','");
		StringBuffer valbuff = new StringBuffer();
		valbuff.append("'").append(valcd).append("'");
		valcd = valbuff.toString();
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
		
		String ulatitude = request.getParameter("uLattitudeTxt");
		String calulatitude = "";
		String calllatitude = "";
		String calllongtitude = "";
		String calrlongtitude = "";
		if(!BeanUtils.isEmpty(ulatitude)){
			if("N".equals(ulatitude.substring(ulatitude.length() - 1))){
				ulatitude = ulatitude.substring(0,ulatitude.length()-1);
				String[] ulatitudeData = ulatitude.split("\\.");
				Double X = Double.valueOf(ulatitudeData[0].trim());
				Double Y = Double.valueOf(ulatitudeData[1].trim());
				calulatitude = String.valueOf(X+(Y/60));
			}
			else{
				ulatitude = ulatitude.substring(0,ulatitude.length()-1);
				String[] ulatitudeData = ulatitude.split("\\.");
				Double A = Double.valueOf(ulatitudeData[0].trim());
				Double B = Double.valueOf(ulatitudeData[1].trim());
				calulatitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		String llatitude = request.getParameter("lLattitudeTxt");
		if(!BeanUtils.isEmpty(llatitude)){
			if("N".equals(llatitude.substring(llatitude.length() - 1))){
				llatitude = llatitude.substring(0,llatitude.length()-1);
				String[] llatitudeData = llatitude.split("\\.");
				Double X = Double.valueOf(llatitudeData[0].trim());
				Double Y = Double.valueOf(llatitudeData[1].trim());
				calllatitude = String.valueOf(X+(Y/60));
			}
			else{
				llatitude = llatitude.substring(0,llatitude.length()-1);
				String[] llatitudeData = llatitude.split("\\.");
				Double A = Double.valueOf(llatitudeData[0].trim());
				Double B = Double.valueOf(llatitudeData[1].trim());
				calllatitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		String llongtitude = request.getParameter("lLontitudeTxt");
		if(!BeanUtils.isEmpty(llongtitude)){
			if("E".equals(llongtitude.substring(llongtitude.length() - 1))){
				llongtitude = llongtitude.substring(0,llongtitude.length()-1);
				String[] llongtitudeData = llongtitude.split("\\.");
				Double X = Double.valueOf(llongtitudeData[0].trim());
				Double Y = Double.valueOf(llongtitudeData[1].trim());
				calllongtitude = String.valueOf(X+(Y/60));
			}
			else{
				llongtitude = llongtitude.substring(0,llongtitude.length()-1);
				String[] llongtitudeData = llongtitude.split("\\.");
				Double A = Double.valueOf(llongtitudeData[0].trim());
				Double B = Double.valueOf(llongtitudeData[1].trim());
				calllongtitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		String rlongtitude = request.getParameter("rLontitudeTxt");
		if(!BeanUtils.isEmpty(rlongtitude)){
			if("E".equals(rlongtitude.substring(rlongtitude.length() - 1))){
				rlongtitude = rlongtitude.substring(0,rlongtitude.length()-1);
				String[] rlongtitudeData = rlongtitude.split("\\.");
				Double X = Double.valueOf(rlongtitudeData[0].trim());
				Double Y = Double.valueOf(rlongtitudeData[1].trim());
				calrlongtitude = String.valueOf(X+(Y/60));
			}
			else{
				rlongtitude = rlongtitude.substring(0,rlongtitude.length()-1);
				String[] rlongtitudeData = rlongtitude.split("\\.");
				Double A = Double.valueOf(rlongtitudeData[0].trim());
				Double B = Double.valueOf(rlongtitudeData[1].trim());
				calrlongtitude = String.valueOf((A+(B/60))*(-1));
			}
			
		}
		ssqadm.setSwsval(valcd);
		ssqadm.setStartDate(StartDt);
		ssqadm.setFinishDate(EndDt);
		ssqadm.setStartTime(StartTime);
		ssqadm.setEndTime(EndTime);
		ssqadm.setTime(time);
		ssqadm.setUpLatitide(calulatitude);
		ssqadm.setLowLatitide(calllatitude);
		ssqadm.setLeftLongtitude(calllongtitude);
		ssqadm.setRightLongtitude(calrlongtitude);
		String fileName = "";

		fileName = name+System.currentTimeMillis()+".csv";
		
		domain.genSwstnDetail(ssqadm,GenFileConstants.filepath+fileName);

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
			    
		    domain.updateHis(name,fileName,"2");
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}

}
