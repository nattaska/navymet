package com.ss.sq.adm.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fission.persistence.service.GenericEntityService;
import com.fission.web.view.extjs.data.StoreData;
import com.fission.web.view.extjs.grid.PagingGridData;
import com.ss.sq.resource.connect;
import com.ss.sq.util.BeanUtils;

@Configurable
public class SSQADM004Domain extends SSQADM002 {

	@Autowired
	private GenericEntityService genericEntityService;
	

	public GenericEntityService getGenericEntityService() {
		return genericEntityService;
	}

	public void setGenericEntityService(
			GenericEntityService genericEntityService) {
		this.genericEntityService = genericEntityService;
	}
	
	private Connection con = null;
	
	String SQL_SEL_VAL = "SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 4 order by pmdldesc ";
	String SQL_SEL_LOC_STATION = "SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 5 order by pmdldesc ";
	//String SQL_SEL_GWS_GRID = "select gwsdat,stn.pmdv2 as \"Country\",stn.pmdedesc as \"Station\", stn.pmdv3 as \"Lattitude\",stn.pmdv4 as \"Lontitude\", COALESCE(gwspp,'') AS gwspp,COALESCE(gwstt,'') as gwstt,COALESCE(gwstd,'') as gwstd,COALESCE(gwstmax,'') as gwstmax,COALESCE(gwstmin,'') as gwstmin,COALESCE(gwscc,'') as gwscc,COALESCE(gwsvv,'') as gwsvv,COALESCE(gwsws,'') as gwsws,COALESCE(gwswd,'') as gwswd,COALESCE(gwsrain24,'') as gwsrain24,COALESCE(pww.pmdcmnt,'') gwsww,COUNT (*) OVER (PARTITION BY 'X') cnt_all,row_number() over() as rec from gws  join prmdtl stn on stn.pmdtbno=5 and stn.pmdentcd=gwsstn left join prmdtl pww on  pww.pmdtbno=7 and pww.pmdentcd=gwsww ";
	String SQL_SEL_GWS_GRID = "select gwsdat,stn.pmdv2 as \"Country\",stn.pmdedesc as \"Station\", stn.pmdv3 as \"Lattitude\",stn.pmdv4 as \"Lontitude\", COALESCE(gwspp,'') AS gwspp," +
			" COALESCE(gwstt,'') as gwstt,COALESCE(gwstd,'') as gwstd,COALESCE(gwstmax,'') as gwstmax,COALESCE(gwstmin,'') as gwstmin,COALESCE(gwscc,'') as gwscc," +
			" COALESCE(gwsvv,'') as gwsvv,COALESCE(gwsws,'') as gwsws,COALESCE(gwswd,'') as gwswd,COALESCE(gwsrain24,'') as gwsrain24,COALESCE(pww.pmdcmnt,'') gwsww," +
			" COUNT (*) OVER (PARTITION BY 'X') cnt_all,row_number() over(ORDER BY gwsdat, stn.pmdv2, stn.pmdedesc, stn.pmdv3, stn.pmdv4) as rec" +
			" from gws join prmdtl stn on stn.pmdtbno=5 and stn.pmdentcd=gwsstn left join prmdtl pww on pww.pmdtbno=7 and pww.pmdentcd=gwsww left join prmdtl pwd on pwd.pmdtbno=8 and pwd.pmdentcd=gwswd ";
	String SQL_GEN_HDR = "select pmdedesc from prmdtl where pmdtbno=4 and pmdentcd ";
	String SQL_GEN_HDR_H = "select prmdtl.pmdv1 from prmdtl where pmdtbno=4 and pmdentcd ";
	String SQL_SEL_COUNTRY = "SELECT DISTINCT pmdv1,pmdv2 FROM prmdtl WHERE pmdtbno = 5 order by pmdv2";
	public StoreData findValiableStoreData() throws SQLException{
			
			List<SSQADM004> list = new ArrayList<SSQADM004>();
		    con = connect.getConnection();
		    PreparedStatement pstmt;
		    SSQADM004 sub = null;
		    pstmt = con.prepareStatement(SQL_SEL_VAL);
		    ResultSet rs = pstmt.executeQuery();
		    
		    while(rs.next())
	        {
	            sub = new SSQADM004();
	            sub.setPmdentcd(rs.getString(1));
	            sub.setPmdldesc(rs.getString(2));
	            
	            list.add(sub);
	        }
		    
		    con.close();
		    pstmt.close();
		    rs.close();
			
			StoreData storeData = new StoreData();
			storeData.setRecords(list);
			storeData.setVarName("valStoreData");
			return storeData;		
		}

	public StoreData findStationStoreData() throws SQLException{

		List<SSQADM004> list = new ArrayList<SSQADM004>();
		con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM004 sub = null;
	    pstmt = con.prepareStatement(SQL_SEL_LOC_STATION);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM004();
            sub.setPmdentcd(rs.getString(1));
            sub.setPmdldesc(rs.getString(2));
            
            list.add(sub);
        }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		
		StoreData storeData = new StoreData();
		storeData.setRecords(list);
		storeData.setVarName("stationStoreData");
		return storeData;
	}
	
	public StoreData findStationStoreData2(String countyId) throws SQLException{

		List<SSQADM004> list = new ArrayList<SSQADM004>();
		con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM004 sub = null;
	    pstmt = con.prepareStatement("SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 5 AND pmdv1 = '"+countyId+"' order by pmdldesc");
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM004();
            sub.setPmdentcd(rs.getString(1));
            sub.setPmdldesc(rs.getString(2));
            
            list.add(sub);
        }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		
		StoreData storeData = new StoreData();
		storeData.setRecords(list);
		storeData.setVarName("stationStoreData");
		return storeData;
	}
	
	public StoreData findCountryStoreData() throws SQLException{

		List<SSQADM004> list = new ArrayList<SSQADM004>();
		con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM004 sub = null;
	    pstmt = con.prepareStatement(SQL_SEL_COUNTRY);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM004();
            sub.setPmdentcd(rs.getString(1));
            sub.setPmdldesc(rs.getString(2));
            
            list.add(sub);
        }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		
		StoreData storeData = new StoreData();
		storeData.setRecords(list);
		storeData.setVarName("countryStoreData");
		return storeData;
	}

	public PagingGridData findGwsDetail(SSQADM004 ssqadm) throws SQLException{
		
		int count = 0;
		List<SSQADM004> list = new ArrayList<SSQADM004>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
		int startQuery = 0;
		int endQuery = 0;
	    startQuery = getStart();
		endQuery = getStart() + getLimit();
		String startDate = ssqadm.getStartDate();
		String endDate = ssqadm.getFinishDate();
		
		if(!BeanUtils.isEmpty(ssqadm.getStartTime())){
			startDate = ssqadm.getStartDate().replace("T00:00", "T"+ssqadm.getStartTime());
		}
		if(!BeanUtils.isEmpty(ssqadm.getEndTime())){
			endDate = ssqadm.getFinishDate().replace("T00:00", "T"+ssqadm.getEndTime());
		}
		else{
			endDate = ssqadm.getFinishDate().replace("T00:00", "T23:59");
		}  
	    StringBuilder strbuilder=new StringBuilder();
	    strbuilder.append(" WHERE 1=1 ");
	    if(!BeanUtils.isEmpty(startDate)){
			strbuilder.append(" AND gwsdat >= '"+startDate+"' ");
		}
		if(!BeanUtils.isEmpty(endDate)){
			strbuilder.append(" AND gwsdat <= '"+endDate+"' ");
		}
		
		if(!BeanUtils.isEmpty(ssqadm.getTime())){
			strbuilder.append(" AND gwsdat::TIME in ("+ssqadm.getTime()+") ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getLowLatitide())){
			strbuilder.append(" AND stn.pmdv3::NUMERIC >= '"+ssqadm.getLowLatitide()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getUpLatitide())){
			strbuilder.append(" AND stn.pmdv3::NUMERIC <= '"+ssqadm.getUpLatitide()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getLeftLongtitude())){
			strbuilder.append(" AND stn.pmdv4::NUMERIC >= '"+ssqadm.getLeftLongtitude()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getRightLongtitude())){
			strbuilder.append(" AND stn.pmdv4::NUMERIC <= '"+ssqadm.getRightLongtitude()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getCountry())&&!"''".equals(ssqadm.getCountry())){
			strbuilder.append(" AND stn.pmdv1 in ('"+ssqadm.getCountry()+"') ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getStation())&&!"''".equals(ssqadm.getStation())){
			strbuilder.append(" AND stn.pmdentcd in ("+ssqadm.getStation()+") ");
		}
	    System.out.println("SELECT * FROM ("+SQL_SEL_GWS_GRID+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "'  ");
	    pstmt = con.prepareStatement("SELECT * FROM ("+SQL_SEL_GWS_GRID+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "' " );
	    ResultSet rs = pstmt.executeQuery();
	    SSQADM004 sub = null;
	    while(rs.next())
	    {
	    	int i = 0;
	        sub = new SSQADM004();
	        sub.setGwsdat(rs.getString(1));
	        sub.setCountry(rs.getString(2));
	        sub.setStation(rs.getString(3));
	        sub.setLatitude(rs.getString(4));
	        sub.setLongtitude(rs.getString(5));
	        sub.setPp(rs.getString(6));
	        sub.setTt(rs.getString(7));
	        sub.setTd(rs.getString(8));
	        sub.setTmax(rs.getString(9));
	        sub.setTmin(rs.getString(10));
	        sub.setCc(rs.getString(11));
	        sub.setVv(rs.getString(12));
	        sub.setWs(rs.getString(13));
	        sub.setWd(rs.getString(14));
	        sub.setRain24(rs.getString(15));
	        sub.setWw(rs.getString(16));
	        if (i == 0) {
				count = Integer.parseInt(rs.getString(17));
			}
	        list.add(sub);
	        i++;
	    }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		
	    PagingGridData pagingGridData = new PagingGridData();
		pagingGridData.setRecords(list);
		pagingGridData.setTotal(count);
		pagingGridData.setSuccess(true);
		return pagingGridData;	
	}
	
	public List<SSQADM004> genGwsDetail(SSQADM004 ssqadm, String fileName) throws SQLException{
		
		List<SSQADM004> list = new ArrayList<SSQADM004>();
		String startDate = ssqadm.getStartDate();
		String endDate = ssqadm.getFinishDate();
		
	    StringBuilder strbuilder=new StringBuilder();
	    strbuilder.append(" WHERE 1=1 ");
	    
	    if(!BeanUtils.isEmpty(ssqadm.getStartTime())){
			startDate = ssqadm.getStartDate().concat(" "+ssqadm.getStartTime());
		}
		if(!BeanUtils.isEmpty(ssqadm.getEndTime())){
			endDate = ssqadm.getFinishDate().concat(" "+ssqadm.getEndTime());
		}
		else{
			endDate = ssqadm.getFinishDate().concat(" 23:59");
		} 
		
	    if(!BeanUtils.isEmpty(ssqadm.getStartDate())){
			strbuilder.append(" AND gwsdat >= to_timestamp('"+startDate+"' ,'DD/MM/YYYY HH24:MI') ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getFinishDate())){
			strbuilder.append(" AND gwsdat <= to_timestamp('"+endDate+"' ,'DD/MM/YYYY HH24:MI')  ");
		}

		if(!BeanUtils.isEmpty(ssqadm.getTime())){
			strbuilder.append(" AND gwsdat::TIME in ("+ssqadm.getTime()+") ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getLowLatitide())){
			strbuilder.append(" AND stn.pmdv3::NUMERIC >= '"+ssqadm.getLowLatitide()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getUpLatitide())){
			strbuilder.append(" AND stn.pmdv3::NUMERIC <= '"+ssqadm.getUpLatitide()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getLeftLongtitude())){
			strbuilder.append(" AND stn.pmdv4::NUMERIC >= '"+ssqadm.getLeftLongtitude()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getRightLongtitude())){
			strbuilder.append(" AND stn.pmdv4::NUMERIC <= '"+ssqadm.getRightLongtitude()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getCountry())&&!"''".equals(ssqadm.getCountry())){
			strbuilder.append(" AND stn.pmdv1 in ('"+ssqadm.getCountry()+"') ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getStation())&&!"''".equals(ssqadm.getStation())){
			strbuilder.append(" AND stn.pmdentcd in ("+ssqadm.getStation()+") ");
		}
	    		
	    	String mergecolmHdr= "gwsdat AS \"DATE TIME\",\"Country\",\"Station\",\"Lattitude\",\"Lontitude\"";
	    	String val = ssqadm.getGwsval();

    		List<SSQADM004> colmnHdr  = extratColumforGenerateHdr(val,"0");
    	    if(colmnHdr.size()>0){		    	
    	    	List<SSQADM004> colmnHdrdata  = extratColumforGenerateHdr(val,"1");
	    	    for(int i=0; i<=colmnHdrdata.size()-1;i++){		    	
	    	    	mergecolmHdr = mergecolmHdr+", "+colmnHdrdata.get(i).getColumHdr()+" AS \""+colmnHdr.get(i).getColumHdr()+"\"";
	    	    }
    	    }		
    	    else{
    	     	mergecolmHdr = mergecolmHdr+" ,gwspp as \"Pressure\",gwstt AS \"Dry Bulb Temperature\",gwstd AS \"Dew Bulb Temperature\",gwstmax AS \"Maximum Temperature\",gwstmin AS \"Minimum Temperature\",gwscc AS \"Total Cloud Cover\",gwsvv AS \"Visibility\",gwsws AS \"Wind Speed\",gwswd AS \"Wind Direction\",gwsrain24 AS \"24 Hours Precipitation\",gwsww AS \"Present Weather\"";        	    
    	    }    		
    	    
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    System.out.println("COPY (SELECT "+mergecolmHdr+" FROM ("+SQL_SEL_GWS_GRID+strbuilder+" ) AS results ORDER BY gwsdat,\"Country\",\"Station\",\"Lattitude\",\"Lontitude\" ) TO '"+fileName+"' WITH CSV HEADER");
	    pstmt = con.prepareStatement("COPY (SELECT "+mergecolmHdr+" FROM ("+SQL_SEL_GWS_GRID+strbuilder+" ) AS results ORDER BY gwsdat,\"Country\",\"Station\",\"Lattitude\",\"Lontitude\" ) TO '"+fileName+"' WITH CSV HEADER");
	    pstmt.execute();
	    
	    con.close();
	    pstmt.close();
		
		return list;	
	}
	
	public List<SSQADM004> extratColumforGenerateHdr(String val,String f) throws SQLException{
		
		List<SSQADM004> list = new ArrayList<SSQADM004>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM004 sub = null;
	    if("0".equals(f)){
	    	System.out.println(SQL_GEN_HDR+" in ("+val+")");
	    	pstmt = con.prepareStatement(SQL_GEN_HDR+" in ("+val+")");
	    }
	    else{
	    	System.out.println(SQL_GEN_HDR_H+" in ("+val+")");
	    	pstmt = con.prepareStatement(SQL_GEN_HDR_H+" in ("+val+")");
	    }
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
	    {
	        sub = new SSQADM004();
	        sub.setColumHdr(rs.getString(1));
	        
	        list.add(sub);
	    }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		return list;		
	}
	
	public void updateHis(String name, String fileName, String meth) throws SQLException {
		// TODO Auto-generated method stub
		 con = connect.getConnection();
		 PreparedStatement pstmt;
		 System.out.println("INSERT INTO status_history VALUES('"+name+"','"+fileName+"','"+meth+"',CURRENT_TIMESTAMP )");
		 pstmt = con.prepareStatement("INSERT INTO status_history VALUES('"+name+"','"+fileName+"','"+meth+"',CURRENT_TIMESTAMP )");
		 pstmt.execute();

	}
	
	public StoreData findStationStoreDataParam() throws SQLException{

		List<SSQADM004> list = new ArrayList<SSQADM004>();
		con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM004 sub = null;
	    pstmt = con.prepareStatement("SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 5 AND pmdv1 = 'JP' order by pmdldesc ");
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM004();
            sub.setPmdentcd(rs.getString(1));
            sub.setPmdldesc(rs.getString(2));
            
            list.add(sub);
        }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		
		StoreData storeData = new StoreData();
		storeData.setRecords(list);
		storeData.setVarName("stationStoreData");
		return storeData;
	}

}
