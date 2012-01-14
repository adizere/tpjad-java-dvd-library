package com.dvdworld.infrastructure;

import java.util.Properties;

import com.dvdworld.business.DvdWorldConstants;

public class DvdWorldConfigInfo {
	
	public String dbConnectionString;
	public String dbUser;
	public String dbPassword;
	public String dbDriver;
	
	public DvdWorldConfigInfo() {
	}
	
	public boolean getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(DvdWorldConstants.dvdWorldConfigPropertiesFile));
			dbConnectionString = properties.getProperty("dvdworld.db.connectionString");
			dbUser = properties.getProperty("dvdworld.db.user");
			dbPassword = properties.getProperty("dvdworld.db.password");
			dbDriver = properties.getProperty("dvdworld.db.driver");
		} catch (Exception e) {
			System.out.println("Error gettting DVDWorld properties: " + e);
			return false;
		}
		
		return true;
	}
	
}
