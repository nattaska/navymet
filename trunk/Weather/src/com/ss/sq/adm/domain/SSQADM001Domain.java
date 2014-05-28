package com.ss.sq.adm.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fission.web.view.extjs.data.StoreData;
import org.springframework.beans.factory.annotation.Configurable;

import com.fission.web.view.extjs.grid.PagingGridData;
import com.ss.sq.entity.UserModel;
import com.ss.sq.resource.connect;
import com.ss.sq.util.BeanUtils;

@Configurable
public class SSQADM001Domain extends SSQADM001{

	private Connection con = null;

	String SQL_VALIDATE_LOGIN = "SELECT * FROM SS_USER WHERE \"USERNAME\" = ? AND \"PASSWORD\" = ?";
	String SQL_INS_USR = "INSERT INTO SS_USER (\"USERNAME\",\"NAME\",\"PASSWORD\",\"ADMIN\") VALUES (?, ?, ?, ?) ";
	String SQL_USER_DTL = "SELECT * FROM SS_USER WHERE \"USERNAME\" = ? AND \"PASSWORD\" = ? AND \"ADMIN\" = 'Y'";
	String SQL_SEL_USR_DTL = "SELECT usr.* ,COUNT (*) OVER (PARTITION BY 'X') cnt_all,row_number() over() as rec ,to_char(CURRENT_TIMESTAMP,'FMYYYYMMDDHH24MI') FROM ss_user usr ";
	String SQL_DEL_USR = "DELETE FROM ss_user WHERE \"USERNAME\" in ";
	String SQL_MOD_USR = "DELETE FROM ss_user WHERE \"USERNAME\" in ";
	public boolean loginValidate(UserModel user)throws Exception
	{
	    boolean valid = false;
	    con = connect.getConnection();
	    PreparedStatement pstmt;

	    pstmt = con.prepareStatement(SQL_VALIDATE_LOGIN);
	    pstmt.setString(1,user.getId());
	    pstmt.setString(2,user.getPassword());
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
	    {
	        valid = true;
	    }
	    con.close();
	    pstmt.close();
	    rs.close();
	     return valid;
	}
	
	public boolean isAdmin(UserModel user)throws Exception
	{
	    boolean valid = false;
	    con = connect.getConnection();
	    PreparedStatement pstmt;

	    pstmt = con.prepareStatement(SQL_USER_DTL);
	    pstmt.setString(1,user.getId());
	    pstmt.setString(2,user.getPassword());
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next())
	    {
	        valid = true;
	    }
	    con.close();
	    pstmt.close();
	    rs.close();
	     return valid;
	}
	
	public void saveUser(UserModel user)throws Exception
	{
	    con = connect.getConnection();
	    PreparedStatement pstmt;

	    pstmt = con.prepareStatement(SQL_INS_USR);
	    pstmt.setString(1,user.getId());
	    pstmt.setString(2,user.getUserName());
	    pstmt.setString(3,user.getPassword());
	    pstmt.setString(4,user.getIsAdmin());
	    pstmt.execute();
	    
	    con.close();
	    pstmt.close();
	}
	
	public PagingGridData findUsrDetail(SSQADM001 ssqadm) throws SQLException{
		
		int count = 0;
		List<SSQADM001> list = new ArrayList<SSQADM001>();
	    con = connect.getConnection();
	    PreparedStatement pstmt;
		int startQuery = 0;
		int endQuery = 0;
	    startQuery = getStart();
		endQuery = getStart() + getLimit();
	    
	    StringBuilder strbuilder=new StringBuilder();
	    strbuilder.append(" WHERE 1=1 ");
	    		if(!BeanUtils.isEmpty(ssqadm.getUid())){
	    			strbuilder.append(" AND usr.\"USERNAME\" like '"+ssqadm.getUid()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getName())){
	    			strbuilder.append(" AND usr.\"NAME\" like '"+ssqadm.getName()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getAdmin())){
	    			strbuilder.append(" AND usr.\"ADMIN\" = '"+ssqadm.getAdmin()+"' ");
	    		}
	    		if(!BeanUtils.isEmpty(ssqadm.getPassword())){
	    			strbuilder.append(" AND usr.\"PASSWORD\" = '"+ssqadm.getPassword()+"' ");
	    		}
	    		
	    System.out.println("SELECT * FROM ("+SQL_SEL_USR_DTL+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "' ");
	    pstmt = con.prepareStatement("SELECT * FROM ("+SQL_SEL_USR_DTL+strbuilder+" ) AS results WHERE results.rec BETWEEN '"+ ((startQuery+1)) +"' AND '" + endQuery + "' ");
	    ResultSet rs = pstmt.executeQuery();
	    SSQADM001 sub = null;
	    while(rs.next())
	    {
	    	int i = 0;
	        sub = new SSQADM001();
	        sub.setUid(rs.getString(1));
	        sub.setName(rs.getString(2));
	        sub.setPassword(rs.getString(3));
	        sub.setAdmin(rs.getString(4));
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
	
		public String deleteTransactionDetail(List<SSQADM001Domain> list) throws Exception {
			String  transaction = " ";
	    	con = connect.getConnection();
		    PreparedStatement pstmt;
		    for(int i=0;i<=list.size()-1;i++){
			    pstmt = con.prepareStatement(SQL_DEL_USR+" ('"+list.get(i).getUid()+"')");
			    pstmt.execute();
			    pstmt.close();
		    }

		    con.close();
		    
			
			return transaction;
		}
		
		public String modifyTransactionDetail(List<SSQADM001Domain> list) throws Exception {
			String  transaction = " ";
	    	con = connect.getConnection();
		    PreparedStatement pstmt;
		    for(int i=0;i<=list.size()-1;i++){
			    pstmt = con.prepareStatement("UPDATE ss_user SET \"NAME\" = '"+list.get(i).getName()+ "' , \"PASSWORD\" = '"+list.get(i).getPassword()+"' , \"ADMIN\" ='"+list.get(i).getAdmin()+"'" +
			    		" WHERE \"USERNAME\" = '"+list.get(i).getUid()+"'");
			    pstmt.execute();
			    pstmt.close();
		    }

		    con.close();
		    
			
			return transaction;
		}
}
