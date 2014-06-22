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
public class SSQADM003Domain extends SSQADM003 {

	private Connection con = null;

	String SQL_SEL_VAL = "SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 2 order by pmdentcd ";
	String SQL_SEL_SWS_GRID = "SELECT swsdat,swslat,swslon,swspp,swstt,swstw,swstd,swsuu,swscc,swstmax,swstmin,swsr,swsvv,swsrain24,COUNT (*) OVER (PARTITION BY 'X') cnt_all,row_number() over(ORDER BY swsdat , swslat , swslon) as rec FROM sws ";
	String SQL_GEN_HDR = "select pmdedesc from prmdtl where pmdtbno=2 and pmdentcd ";
	String SQL_GEN_COLUM = "select pmdv1 from prmdtl where pmdtbno=2 and pmdentcd ";


	public StoreData findValiableStoreData() throws SQLException{
		
		List<SSQADM003> list = new ArrayList<SSQADM003>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM003 sub = null;
	    pstmt = con.prepareStatement(SQL_SEL_VAL);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM003();
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
	
	public PagingGridData findSwstnDetail(SSQADM003 ssqadm) throws SQLException{
		
		int count = 0;
		List<SSQADM003> list = new ArrayList<SSQADM003>();
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
	    			strbuilder.append(" AND swsdat >= '"+startDate+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(endDate)){
	    			strbuilder.append(" AND swsdat <= '"+endDate+"' ");
	    		}

	    		if(!BeanUtils.isEmpty(ssqadm.getTime())){
	    			strbuilder.append(" AND swsdat::TIME in ("+ssqadm.getTime()+") ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getLowLatitide())){
	    			strbuilder.append(" AND swslat::NUMERIC >= '"+ssqadm.getLowLatitide()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getUpLatitide())){
	    			strbuilder.append(" AND swslat::NUMERIC <= '"+ssqadm.getUpLatitide()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getLeftLongtitude())){
	    			strbuilder.append(" AND swslon::NUMERIC >= '"+ssqadm.getLeftLongtitude()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getRightLongtitude())){
	    			strbuilder.append(" AND swslon::NUMERIC <= '"+ssqadm.getRightLongtitude()+"' ");
	    		}
	    System.out.println("SELECT * FROM ("+SQL_SEL_SWS_GRID+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "'  ");
	    pstmt = con.prepareStatement("SELECT * FROM ("+SQL_SEL_SWS_GRID+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "'  " );
	    ResultSet rs = pstmt.executeQuery();
	    SSQADM003 sub = null;
	    while(rs.next())
	    {
	    	int i = 0;
	        sub = new SSQADM003();
	        sub.setSwsdattm(rs.getString(1));
	        sub.setLatitude(rs.getString(2));
	        sub.setLongtitude(rs.getString(3));
	        sub.setPp(rs.getString(4));
	        sub.setTt(rs.getString(5));
	        sub.setTw(rs.getString(6));
	        sub.setTd(rs.getString(7));
	        sub.setUu(rs.getString(8));
	        sub.setCc(rs.getString(9));
	        sub.settMax(rs.getString(10));
	        sub.settMin(rs.getString(11));
	        sub.setSr(rs.getString(12));
	        sub.setVv(rs.getString(13));
	        sub.setRain24(rs.getString(14));
	        if (i == 0) {
				count = Integer.parseInt(rs.getString(15));
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
	
public  List<SSQADM003> genSwstnDetail(SSQADM003 ssqadm, String fileName) throws SQLException{
		
		List<SSQADM003> list = new ArrayList<SSQADM003>();
		String startDate = ssqadm.getStartDate();
		String endDate = ssqadm.getFinishDate();
		
	    StringBuilder strbuilder=new StringBuilder();
	    strbuilder.append(" FROM sws WHERE 1=1 ");
		    if(!BeanUtils.isEmpty(ssqadm.getStartTime())){
				startDate = ssqadm.getStartDate().concat(" "+ssqadm.getStartTime());
			}
			if(!BeanUtils.isEmpty(ssqadm.getEndTime())){
				endDate = ssqadm.getFinishDate().concat(" "+ssqadm.getEndTime());
			}
			else if(BeanUtils.isEmpty(ssqadm.getEndTime()) && !BeanUtils.isEmpty(ssqadm.getFinishDate())){
				endDate = ssqadm.getFinishDate().concat(" 23:59");
			} 
		
		    if(!BeanUtils.isEmpty(startDate)){
				strbuilder.append(" AND swsdat >= to_timestamp('"+startDate+"' ,'DD/MM/YYYY HH24:MI') ");
			}
			if(!BeanUtils.isEmpty(endDate)){
				strbuilder.append(" AND swsdat <= to_timestamp('"+endDate+"' ,'DD/MM/YYYY HH24:MI')  ");
			}

			if(!BeanUtils.isEmpty(ssqadm.getTime())){
				strbuilder.append(" AND swsdat::TIME in ("+ssqadm.getTime()+") ");
			}
			if(!BeanUtils.isEmpty(ssqadm.getLowLatitide())){
				strbuilder.append(" AND swslat::NUMERIC >= '"+ssqadm.getLowLatitide()+"' ");
			}
			if(!BeanUtils.isEmpty(ssqadm.getUpLatitide())){
				strbuilder.append(" AND swslat::NUMERIC <= '"+ssqadm.getUpLatitide()+"' ");
			}
			if(!BeanUtils.isEmpty(ssqadm.getLeftLongtitude())){
				strbuilder.append(" AND swslon::NUMERIC >= '"+ssqadm.getLeftLongtitude()+"' ");
			}
			if(!BeanUtils.isEmpty(ssqadm.getRightLongtitude())){
				strbuilder.append(" AND swslon::NUMERIC <= '"+ssqadm.getRightLongtitude()+"' ");
			}
			
			List<SSQADM003> colmn  = extratColumforGenerate(ssqadm);
			List<SSQADM003> colmnHdr  = extratColumforGenerateHdr(ssqadm);
		    String mergecolm = "";
		    for(int i=0; i<=colmn.size()-1;i++){		    	
		    	mergecolm = mergecolm+", COALESCE("+colmn.get(i).getStation()+",0) AS \""+colmnHdr.get(i).getColumHdr()+"\""; 	
		    }

		con = null;
	    con = connect.getConnection();
	    PreparedStatement pstmt;	
	    System.out.println("COPY (SELECT swsdat as \"Date Time\",swslat as \"Latitude\",swslon as \"Longtitude\""+mergecolm+strbuilder+" ORDER BY swsdat , swslat , swslon) TO '"+fileName+"' WITH CSV HEADER");
	    pstmt = con.prepareStatement("COPY (SELECT swsdat as \"Date Time\",swslat as \"Latitude\",swslon as \"Longtitude\""+mergecolm+strbuilder+" ORDER BY swsdat , swslat , swslon) TO '"+fileName+"' WITH CSV HEADER");
	    pstmt.execute();
	    
	    con.close();
	    pstmt.close();
		
		return list;	
		}

	public List<SSQADM003> extratColumforGenerate(SSQADM003 ssqadm) throws SQLException{
		
		List<SSQADM003> list = new ArrayList<SSQADM003>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM003 sub = null;
	    System.out.println(SQL_GEN_COLUM+" in ("+ssqadm.getSwsval()+")");
	    pstmt = con.prepareStatement(SQL_GEN_COLUM+" in ("+ssqadm.getSwsval()+")");
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
	    {
	        sub = new SSQADM003();
	        sub.setStation(rs.getString(1));
	        
	        list.add(sub);
	    }
	    
	    con.close();
	    pstmt.close();
	    rs.close();
		return list;		
	}
	
public List<SSQADM003> extratColumforGenerateHdr(SSQADM003 ssqadm) throws SQLException{
		
		List<SSQADM003> list = new ArrayList<SSQADM003>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM003 sub = null;
	    System.out.println(SQL_GEN_HDR+" in ("+ssqadm.getSwsval()+")");
	    pstmt = con.prepareStatement(SQL_GEN_HDR+" in ("+ssqadm.getSwsval()+")");
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
	    {
	        sub = new SSQADM003();
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
