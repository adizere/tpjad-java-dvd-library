package com.dvdworld.model;

public class User {
	// INT
	int id = -1;
	// VARCHAR(50)
	String username;
	// VARCHAR(100)
	String name;
	// VARCHAR(30)
	String password;

	public User() {
	}
	
	public User(int id, String username, String name, String password) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String toString() {
		return "User[id="+id+",username="+username+",name="+name+",password="+password+"]";
	}
}
