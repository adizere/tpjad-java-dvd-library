package com.dvdworld.model;

//
// This is what we are borrowing.
//
public class Dvd {
	int id = -1;
	String title;
	String description;
	int quantity;
	double price;
	
	public Dvd(String title) {
		this.title = title;
	}
	
	public Dvd(String title, double price) {
		this.title = title;
		this.price = price;
	}
	
	public Dvd(String title, String description, int quantity, double price) {
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		return "DVD[id="+id+",title="+title+",description="+description+",quantity="+quantity+",price="+price;
	}
}
