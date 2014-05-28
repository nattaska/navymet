package com.ss.sq.resource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connect {
    public static void main(String[]args) throws SQLException
    {
        connect.getConnection();
    }
public static Connection getConnection() throws SQLException {
    Connection connection = null;
    Properties propertiesFile = new Properties(); 
	//		propertiesFile.load(new FileInputStream("src/database.properties")); 
	InputStream in = connect.class.getResourceAsStream("database.properties");
	try {
		propertiesFile.load(in);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
    try{
    	
//        String driverName = "org.postgresql.Driver";
        Class.forName(propertiesFile.getProperty("driverClassName"));

        String url = propertiesFile.getProperty("url");
        String username = propertiesFile.getProperty("username");
        String password = propertiesFile.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connect Complete");
    }
    catch (ClassNotFoundException e)
    {
        System.out.print("Can't Fond JDBC Driver for connect Database");
    }
    catch (SQLException e)
    {
        System.out.print("Can't Connect Database");
    }

    return connection;
}


    }



