	package com.ss.sq.resource;
	
	import java.io.FileInputStream; 
	import java.io.IOException; 
	import java.util.Enumeration; 
	import java.util.Properties; 
	
	
	public class ReadPropFile { 
	
	public static void main(String[] args) { 
	getData(); 
	} 
	
	public static void getData(){ 
	try{ 
	Properties propertiesFile = new Properties(); 
	propertiesFile.load(new FileInputStream("src/database.properties")); 
	String studentName = propertiesFile.getProperty("name"); 
	String roll = propertiesFile.getProperty("roll"); 
	System.out.println("Student Name :: "+studentName); 
	System.out.println("Roll Number :: "+roll); 
	
	//Fetch all the Properties. 
	
	String key; 
	Enumeration e = propertiesFile.propertyNames(); 
	while (e.hasMoreElements()) { 
	key = (String)e.nextElement(); 
	System.out.println(key+" "+propertiesFile.getProperty(key)); 
	} 
	
	}catch(IOException e){ 
	e.printStackTrace(); 
	} 
	} 
	} 