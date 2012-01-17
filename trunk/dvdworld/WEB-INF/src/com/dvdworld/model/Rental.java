package com.dvdworld.model;

import java.util.Date;

import com.dvdworld.model.Dvd;

public class Rental {
	int id = -1;
	// From here we store the DVD id in the DB.
	Dvd dvd;
	// From here we store the user id in the DB.
	User user;
	int quantity;
	// When was the DVD borrowed?
	Date startDate;
	// When must be the DVD brought back?
	Date dueDate;
	// What was the DVD actually brought back?
	Date endDate;
	// Rental has started, meaning that the Administrator performed Proceed operation.
	boolean rentalStarted = false;
	
	public Rental() {
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setDvd(Dvd dvd) {
		this.dvd = dvd;
	}
	
	public Dvd getDvd() {
		return this.dvd;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}

	public void setRentalStarted(boolean rentalStarted) {
		this.rentalStarted = rentalStarted;
	}
	
	public boolean getRentalStarted() {
		return this.rentalStarted;
	}
	
	public String toString() {
		return "Rental[id="+id+","+dvd.toString()+"]";
	}
}
