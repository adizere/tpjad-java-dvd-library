package com.dvdworld.services;

import java.util.Date;

import com.dvdworld.model.Dvd;
import com.dvdworld.model.User;
import com.dvdworld.model.Rental;

public interface DvdWorldDao {
	
	//////////////////////////////////////////////////////////////////////////
	// Various operations
	//////////////////////////////////////////////////////////////////////////
	
	public String hello();
	
	//////////////////////////////////////////////////////////////////////////
	// DVD operations
	//////////////////////////////////////////////////////////////////////////
	
    public Dvd readDvd(int i);
    public void createOrUpdateDvd(Dvd dvd);
    public Dvd[] findDvds();
    
	//////////////////////////////////////////////////////////////////////////
	// Cart operations
	//////////////////////////////////////////////////////////////////////////
    
    public Rental[] getCartRentals();
    public boolean addToCart(Rental rental);
    public void removeFromCart(Dvd dvd);
    public boolean checkOut(Date dueDate);
    public void emptyCart();
    
	//////////////////////////////////////////////////////////////////////////
	// User operations
	//////////////////////////////////////////////////////////////////////////
    
    public User getUser(int id);
    public User getUser(String username);
    public User[] getAllUsers();
    
	//////////////////////////////////////////////////////////////////////////
	// Rental operations
	//////////////////////////////////////////////////////////////////////////
    
    public Rental[] getRentalsByUser(User user);
    public Rental[] getOpenRentalsByUser(User user);
    public Rental[] getCurrentRentalsByUser(User user);
    public Rental[] getClosedRentalsByUser(User user);
    public Rental getRentalById(int rentalId);
    public boolean removeRental(Rental rental);
    public boolean proceedAllRentals(User user);
    public boolean endRent(Rental rental);
}