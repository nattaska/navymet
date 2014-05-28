package com.ss.sq.adm.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

import com.fission.web.view.extjs.data.StoreData;
import com.fission.web.view.extjs.grid.PagingGridData;
import com.ss.sq.resource.connect;
import com.ss.sq.util.BeanUtils;

@Configurable
public class SSQADM005Domain extends SSQADM002 {

	private Connection con = null;

	String SQL_SEL_VAL = "SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 3 order by pmdentcd ";
	String SQL_SEL_WAM_GRID = "SELECT wamdat,wamlat,wamlon,wamwavht,wamwavdir,wamwinspd,wamfrcvlc,wamwindir,wamwavmean,wamwavpeak,wamdragcoef,wamwavstr,COUNT (*) OVER (PARTITION BY 'X') cnt_all,row_number() over(ORDER BY wamdat,wamlat,wamlon) as rec FROM wam ";
	String SQL_GEN_COLUM = "select pmdv1 from prmdtl where pmdtbno=3 and pmdentcd ";
	String SQL_GEN_HDR = "select ''''||pmdedesc||'''' from prmdtl where pmdtbno=3 and pmdentcd ";
	

	public StoreData findValiableStoreData() throws SQLException{
		
		List<SSQADM005> list = new ArrayList<SSQADM005>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM005 sub = null;
	    pstmt = con.prepareStatement(SQL_SEL_VAL);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM005();
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
	
public PagingGridData findWantnDetail(SSQADM005 ssqadm) throws SQLException{
		
		int count = 0;
		List<SSQADM005> list = new ArrayList<SSQADM005>();
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
	    			strbuilder.append(" AND wamdat >= '"+startDate+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(endDate)){
	    			strbuilder.append(" AND wamdat <= '"+endDate+"' ");
	    		}
	    		
	    		if(!BeanUtils.isEmpty(ssqadm.getTime())){
	    			strbuilder.append(" AND wamdat::TIME in ("+ssqadm.getTime()+") ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getLowLatitide())){
	    			strbuilder.append(" AND wamlat::NUMERIC >= '"+ssqadm.getLowLatitide()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getUpLatitide())){
	    			strbuilder.append(" AND wamlat::NUMERIC <= '"+ssqadm.getUpLatitide()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getLeftLongtitude())){
	    			strbuilder.append(" AND wamlon::NUMERIC >= '"+ssqadm.getLeftLongtitude()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getRightLongtitude())){
	    			strbuilder.append(" AND wamlon::NUMERIC <= '"+ssqadm.getRightLongtitude()+"' ");
	    		}
	    System.out.println("SELECT * FROM ("+SQL_SEL_WAM_GRID+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "'  ");
	    pstmt = con.prepareStatement("SELECT * FROM ("+SQL_SEL_WAM_GRID+strbuilder+"  ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "' " );
	    ResultSet rs = pstmt.executeQuery();
	    SSQADM005 sub = null;
	    while(rs.next())
	    {
	    	int i = 0;
	        sub = new SSQADM005();
	        sub.setWamdat(rs.getString(1));
	        sub.setLatitude(rs.getString(2));
	        sub.setLongtitude(rs.getString(3));
	        sub.setWavht(rs.getString(4));
	        sub.setWavdir(rs.getString(5));
	        sub.setWinspd(rs.getString(6));
	        sub.setFrcvlc(rs.getString(7));
	        sub.setWindir(rs.getString(8));
	        sub.setWavmean(rs.getString(9));
	        sub.setWavpeak(rs.getString(10));
	        sub.setDeagcoef(rs.getString(11));
	        sub.setWavstr(rs.getString(12));
	        if (i == 0) {
				count = Integer.parseInt(rs.getString(13));
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

public  List<SSQADM005> genWantnDetail(SSQADM005 ssqadm) throws SQLException{
	
	List<SSQADM005> list = new ArrayList<SSQADM005>();
	String startDate = ssqadm.getStartDate();
	String endDate = ssqadm.getFinishDate();
	
    StringBuilder strbuilder=new StringBuilder();
    strbuilder.append(" FROM wam WHERE 1=1 ");
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
			strbuilder.append(" AND wamdat >= to_timestamp('"+startDate+"' ,'DD/MM/YYYY HH24:MI') ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getFinishDate())){
			strbuilder.append(" AND wamdat <= to_timestamp('"+endDate+"' ,'DD/MM/YYYY HH24:MI') ");
		}
		
		if(!BeanUtils.isEmpty(ssqadm.getTime())){
			strbuilder.append(" AND wamdat::TIME in ("+ssqadm.getTime()+") ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getLowLatitide())){
			strbuilder.append(" AND wamlat::NUMERIC >= '"+ssqadm.getLowLatitide()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getUpLatitide())){
			strbuilder.append(" AND wamlat::NUMERIC <= '"+ssqadm.getUpLatitide()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getLeftLongtitude())){
			strbuilder.append(" AND wamlon::NUMERIC >= '"+ssqadm.getLeftLongtitude()+"' ");
		}
		if(!BeanUtils.isEmpty(ssqadm.getRightLongtitude())){
			strbuilder.append(" AND wamlon::NUMERIC <= '"+ssqadm.getRightLongtitude()+"' ");
		}
		
		List<SSQADM005> colmn  = extratColumforGenerate(ssqadm);
	    String mergecolm = "";
	    String mergecolmHdr= "";
	    for(int i=0; i<=colmn.size()-1;i++){		    	
	    	mergecolm = mergecolm+" ||\',\'|| COALESCE("+colmn.get(i).getStation()+",0)"; 	
	    }
	    List<SSQADM005> colmnHdr  = extratColumforGenerateHdr(ssqadm);
	    for(int i=0; i<=colmnHdr.size()-1;i++){		    	
	    	mergecolmHdr = mergecolmHdr+" ||\',\'|| "+colmnHdr.get(i).getColumHdr(); 	
	    }
	con = null;
    con = connect.getConnection();
    PreparedStatement pstmt;	
    System.out.println("SELECT wamdat||','||wamlat||','||wamlon"+mergecolm+"||'\n',to_char(CURRENT_TIMESTAMP,'FMYYYYMMDDHH24MI') ,'Date Time'||','||'Latitude'||','||'Longtitude'"+mergecolmHdr+strbuilder+ " ORDER BY wamdat , wamlat , wamlon");
    pstmt = con.prepareStatement("SELECT wamdat||','||wamlat||','||wamlon"+mergecolm+"||'\n',to_char(CURRENT_TIMESTAMP,'FMYYYYMMDDHH24MI') ,'Date Time'||','||'Latitude'||','||'Longtitude'"+mergecolmHdr+strbuilder+ " ORDER BY wamdat , wamlat , wamlon");
    ResultSet rs = pstmt.executeQuery();
    SSQADM005 sub = null;
    while(rs.next())
    {
    	int i = 0;
        sub = new SSQADM005();
        sub.setWamval(rs.getString(1));
        if (i == 0) {
        	sub.setGenDate(rs.getString(2));
        	sub.setColumHdr(rs.getString(3));
		}
        list.add(sub);
        i++;
    }
    
    con.close();
    pstmt.close();
    rs.close();
	
	return list;	
	}

public List<SSQADM005> extratColumforGenerate(SSQADM005 ssqadm) throws SQLException{
	
	List<SSQADM005> list = new ArrayList<SSQADM005>();
    con = connect.getConnection();
    PreparedStatement pstmt;
    SSQADM005 sub = null;
    System.out.println(SQL_GEN_COLUM+" in ("+ssqadm.getWamval()+")");
    pstmt = con.prepareStatement(SQL_GEN_COLUM+" in ("+ssqadm.getWamval()+")");
    ResultSet rs = pstmt.executeQuery();
    
    while(rs.next())
    {
        sub = new SSQADM005();
        sub.setStation(rs.getString(1));
        
        list.add(sub);
    }
    
    con.close();
    pstmt.close();
    rs.close();
	return list;		
}
public List<SSQADM005> extratColumforGenerateHdr(SSQADM005 ssqadm) throws SQLException{
	
	List<SSQADM005> list = new ArrayList<SSQADM005>();
    con = connect.getConnection();
    PreparedStatement pstmt;
    SSQADM005 sub = null;
    System.out.println(SQL_GEN_HDR+" in ("+ssqadm.getWamval()+")");
    pstmt = con.prepareStatement(SQL_GEN_HDR+" in ("+ssqadm.getWamval()+")");
    ResultSet rs = pstmt.executeQuery();
    
    while(rs.next())
    {
        sub = new SSQADM005();
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
}
