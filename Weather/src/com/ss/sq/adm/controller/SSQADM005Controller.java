package com.ss.sq.adm.controller;

import java.io.OutputStream;
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
import com.ss.sq.adm.domain.SSQADM005;
import com.ss.sq.adm.domain.SSQADM005Domain;
import com.ss.sq.util.BeanUtils;

@Controller
public class SSQADM005Controller {
	
	@RequestMapping(method = RequestMethod.GET, value="/SSQADM005.htm")
	public ModelMap init(){
		List<StoreData> referenceStoreDatas = new ArrayList<StoreData>();
		List<Data> referenceData = new ArrayList<Data>();
		SSQADM005Domain domain = new SSQADM005Domain();
		try {
			referenceStoreDatas.add(domain.findValiableStoreData());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		Data isAdmin = new Data();
		isAdmin.setVarName("isAdmin");
		referenceData.add(isAdmin);

		FormData formData = new FormData();
		formData.setReferenceDatas(referenceData);
		formData.setReferenceStoreDatas(referenceStoreDatas);
		return formData.createModelMap();
    }

	
	@RequestMapping(value = "/SSQADM005.htm", method = RequestMethod.POST, params = "method=search" )
	@SpringControllerMethod
	public static void findTransactionDetail(@ModelAttribute SSQADM005Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM005 ssqadm = new SSQADM005();
		String valcd = request.getParameter("valdata");
		valcd=valcd.replace(",", "','");
		StringBuffer valbuff = new StringBuffer();
		valbuff.append("'").append(valcd).append("'");
		valcd = valbuff.toString();
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
		ssqadm.setWavval(valcd);
		ssqadm.setStartDate(StartDt);
		ssqadm.setFinishDate(EndDt);
		ssqadm.setStartTime(StartTime);
		ssqadm.setEndTime(EndTime);
		ssqadm.setTime(time);
		ssqadm.setUpLatitide(calulatitude);
		ssqadm.setLowLatitide(calllatitude);
		ssqadm.setLeftLongtitude(calllongtitude);
		ssqadm.setRightLongtitude(calrlongtitude);
		GridData gridData = domain.findWantnDetail(ssqadm);
		gridData.responseJson(response);
	}
	
	@RequestMapping(value = "/SSQADM005.htm", method = RequestMethod.POST, params = "method=genReport" )
	@SpringControllerMethod
	public static void genTransactionDetail(@ModelAttribute SSQADM005Domain domain,
			HttpServletRequest request, HttpServletResponse response) throws SQLException{
		SSQADM005 ssqadm = new SSQADM005();
		String name = request.getSession().getAttribute("username").toString();
		String valcd = request.getParameter("valdata");
		valcd=valcd.replace(",", "','");
		StringBuffer valbuff = new StringBuffer();
		valbuff.append("'").append(valcd).append("'");
		valcd = valbuff.toString();
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
		ssqadm.setWamval(valcd);
		ssqadm.setStartDate(StartDt);
		ssqadm.setFinishDate(EndDt);
		ssqadm.setStartTime(StartTime);
		ssqadm.setEndTime(EndTime);
		ssqadm.setTime(time);
		ssqadm.setUpLatitide(calulatitude);
		ssqadm.setLowLatitide(calllatitude);
		ssqadm.setLeftLongtitude(calllongtitude);
		ssqadm.setRightLongtitude(calrlongtitude);

		List<SSQADM005> data = domain.genWantnDetail(ssqadm);
		String fileName = "";

		 if(!data.isEmpty()){
			fileName = name+data.get(0).getGenDate();
		}
		 else{
			fileName = name+System.currentTimeMillis();
		}	

		response.setContentType("application/ms-excel"); // or you can use text/csv
		response.setHeader("Content-Disposition", "attachment; filename="+fileName+".csv"); 
		StringBuilder strbuilder=new StringBuilder();
		try {
		    // Write the header line
		    OutputStream out = response.getOutputStream();
		    String header = data.get(0).getColumHdr()+"\n";
		    out.write(header.getBytes());
		    // Write the content
		    if(!data.isEmpty()){
		    	for(int i=0; i<=data.size()-1;i++){
				     strbuilder.append(data.get(i).getWamval());
				     }
		    	out.write(strbuilder.toString().getBytes());
		    }		    		    
		    out.flush();
		    domain.updateHis(name,fileName,"3");
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}

}
