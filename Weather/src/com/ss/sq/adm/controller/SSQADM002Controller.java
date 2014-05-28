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
import com.ss.sq.adm.domain.SSQADM002;
import com.ss.sq.adm.domain.SSQADM002Domain;
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
		List<SSQADM002> data = domain.genAwstnDetail(ssqadm);
		
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
			    String header = "Date Time, Station, Valiable , Value\n";
			    out.write(header.getBytes());
			    // Write the content
			    if(!data.isEmpty()){
				    for(int i=0; i<=data.size()-1;i++){
				     strbuilder.append(data.get(i).getAwsdattm()+","+data.get(i).getStation()+","+data.get(i).getAwsprm()+","+data.get(i).getAwsval()+"\n");
				    }
				    out.write(strbuilder.toString().getBytes());
			    }
			    out.flush();
			    
			    domain.updateHis(name,fileName,"1");
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}

}
