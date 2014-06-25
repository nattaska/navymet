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
public class SSQADM002Domain extends SSQADM002 {


	private Connection con = null;

	String SQL_SEL_VAL = "SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 1 order by pmdentcd ";
	String SQL_SEL_LOC_STATION = "SELECT pmdentcd,pmdldesc FROM prmdtl WHERE pmdtbno = 6 order by pmdentcd ";
	String SQL_SEL_AWSTN_DTL = "SELECT awstn.awsdattm, prmdtl.pmdedesc AS station ,(select 	pmdedesc from prmdtl where prmdtl.pmdtbno='1' and prmdtl.pmdentcd=awstn.awsprm) ,awstn.awsval AS VALUE ,COUNT (*) OVER (PARTITION BY 'X') cnt_all,row_number() over(ORDER BY awsdattm , prmdtl.pmdedesc , pmdedesc) as rec ,to_char(CURRENT_TIMESTAMP,'FMYYYYMMDDHH24MI') " +
			"FROM awstn JOIN prmdtl ON awstn.awsstnno::TEXT = prmdtl.pmdentcd::TEXT AND prmdtl.pmdtbno in (6) ";
	String SQL_GEN_AWSTN_DTL = "SELECT awstn.awsdattm as \"Date Time\", prmdtl.pmdedesc AS station ,(select 	pmdedesc from prmdtl where prmdtl.pmdtbno='1' and prmdtl.pmdentcd=awstn.awsprm) AS Valiable ,awstn.awsval AS VALUE " +
			"FROM awstn JOIN prmdtl ON awstn.awsstnno::TEXT = prmdtl.pmdentcd::TEXT AND prmdtl.pmdtbno in (6) ";


	public StoreData findValiableStoreData() throws SQLException{
		
		List<SSQADM002> list = new ArrayList<SSQADM002>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM002 sub = null;
	    pstmt = con.prepareStatement(SQL_SEL_VAL);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM002();
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
		
		List<SSQADM002> list = new ArrayList<SSQADM002>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    SSQADM002 sub = null;
	    pstmt = con.prepareStatement(SQL_SEL_LOC_STATION);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
        {
            sub = new SSQADM002();
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

	public PagingGridData findAwstnDetail(SSQADM002 ssqadm) throws SQLException{
	
	int count = 0;
	List<SSQADM002> list = new ArrayList<SSQADM002>();
    con = connect.getConnection();
    PreparedStatement pstmt;
	int startQuery = 0;
	int endQuery = 0;
    startQuery = getStart();
	endQuery = getStart() + getLimit();
	String startDate = ssqadm.getStartDate();
	String endDate = ssqadm.getFinishDate();
    
    StringBuilder strbuilder=new StringBuilder();
    strbuilder.append(" WHERE awsprm in ("+ssqadm.getAwsprm()+") AND awsstnno in ("+ssqadm.getStation()+")");
    		if(!BeanUtils.isEmpty(ssqadm.getStartTime())){
    			startDate = ssqadm.getStartDate().replace("T00:00", "T"+ssqadm.getStartTime());
    		}
    		if(!BeanUtils.isEmpty(ssqadm.getEndTime())){
    			endDate = ssqadm.getFinishDate().replace("T00:00", "T"+ssqadm.getEndTime());
    		}
    		else{
    			endDate = ssqadm.getFinishDate().replace("T00:00", "T23:59");
    		}       
    		if(!BeanUtils.isEmpty(startDate)){
    			strbuilder.append(" AND awsdattm >= '"+startDate+"' ");
    		}
    		if(!BeanUtils.isEmpty(endDate)){
    			strbuilder.append(" AND awsdattm <= '"+endDate+"' ");
    		}  		
    		
    		if(!BeanUtils.isEmpty(ssqadm.getTime())){
    			strbuilder.append(" AND awsdattm::TIME in ("+ssqadm.getTime()+") ");
    		}
    System.out.println("SELECT * FROM ("+SQL_SEL_AWSTN_DTL+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "'  ");
    pstmt = con.prepareStatement("SELECT * FROM ("+SQL_SEL_AWSTN_DTL+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "'  ");
    ResultSet rs = pstmt.executeQuery();
    SSQADM002 sub = null;
    while(rs.next())
    {
    	int i = 0;
        sub = new SSQADM002();
        sub.setAwsdattm(rs.getString(1));
        sub.setStation(rs.getString(2));
        sub.setAwsprm(rs.getString(3));       
        sub.setAwsval(rs.getString(4));
        if (i == 0) {
			count = Integer.parseInt(rs.getString(5));
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
	
	public List<SSQADM002> genAwstnDetail(SSQADM002 ssqadm, String fileName) throws SQLException{
		
		List<SSQADM002> list = new ArrayList<SSQADM002>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
	    String startDate = ssqadm.getStartDate();
		String endDate = ssqadm.getFinishDate();
		
	    StringBuilder strbuilder=new StringBuilder();
	    strbuilder.append(" WHERE awsprm in ("+ssqadm.getAwsprm()+") AND awsstnno in ("+ssqadm.getStation()+")");
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
					strbuilder.append(" AND awsdattm >= to_timestamp('"+startDate+"' ,'DD/MM/YYYY HH24:MI') ");
				}
				if(!BeanUtils.isEmpty(endDate)){
					strbuilder.append(" AND awsdattm <= to_timestamp('"+endDate+"' ,'DD/MM/YYYY HH24:MI') ");
				}  		
	    		if(!BeanUtils.isEmpty(ssqadm.getTime())){
	    			strbuilder.append(" AND awsdattm::TIME in ("+ssqadm.getTime()+") ");
	    		}
	    System.out.println("COPY (SELECT * FROM ("+SQL_GEN_AWSTN_DTL+strbuilder+" ) AS results ORDER BY awsdattm , station , pmdedesc) TO '"+fileName+"' WITH CSV HEADER");
	    pstmt = con.prepareStatement("COPY (SELECT * FROM ("+SQL_GEN_AWSTN_DTL+strbuilder+" ) AS results ORDER BY awsdattm , station , Valiable) TO '"+fileName+"' WITH CSV HEADER");
	    pstmt.execute();
	    
	    con.close();
	    pstmt.close();
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
