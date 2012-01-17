package com.dvdworld.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.dvdworld.business.DvdWorldBusinessUtils;
import com.dvdworld.db.DvdWorldDbBroker;
import com.dvdworld.dbg.DWDbg;
import com.dvdworld.infrastructure.DvdWorldConfigInfo;
import com.dvdworld.model.Dvd;
import com.dvdworld.model.User;
import com.dvdworld.model.Rental;
import com.dvdworld.web.Cart;

public class DvdWorldDaoStub implements DvdWorldDao {
    private long id = 0;
    private Map<Long, Dvd> dvds = new HashMap<Long, Dvd>();
    private Cart cart = new Cart();

    private DvdWorldDbBroker dbBroker = null;
    private DvdWorldConfigInfo configInfo = null;
    
    private boolean useMocks = false;
    
    public DvdWorldDaoStub() {
    	configInfo = new DvdWorldConfigInfo();
    	configInfo.getProperties();
    	dbBroker = new DvdWorldDbBroker(configInfo);
    }

	//////////////////////////////////////////////////////////////////////////
	// Various operations
	//////////////////////////////////////////////////////////////////////////
    
    public String hello() {
    	return dbBroker.hello();
    }
    
	//////////////////////////////////////////////////////////////////////////
	// DVD operations
	//////////////////////////////////////////////////////////////////////////
    
    //
    // Basic create and update function.
    //
    public void createOrUpdateDvd(Dvd dvd) {
        if (dvd.getId() == -1) {
            id++;
            dvd.setId((int)id);
        }
        dvds.put(new Long(dvd.getId()), dvd);
        System.out.println("SAVE: " + dvd);
    }

    //
    // Get all DVDs.
    //
    public Dvd[] findDvds() {
    	if (this.useMocks) {
	        Dvd[] dvdList = dvds.values().toArray(new Dvd[] {});
	        System.out.println("Returning " + dvdList.length + " DVD MOCK(S):");
	        for (int i = 0; i < dvdList.length; i++) {
	            System.out.println(" > " + dvdList[i]);
	        }
	        return dvdList;
    	} else {
    		List<Dvd> dvdList = this.dbBroker.getAllDvds();
    		Dvd[] dvdArray = (Dvd[])dvdList.toArray(new Dvd[]{});
	        System.out.println("Returning " + dvdList.size() + " DVD(s):");
	        for (int i = 0; i < dvdArray.length; i++) {
	            System.out.println(" > " + dvdArray[i]);
	        }
	        return dvdArray;
    	}
    }
    
    //
    // Get a specific DVD.
    //
    public Dvd readDvd(int id) {
    	if (this.useMocks) {
    		return dvds.get(DvdWorldBusinessUtils.intToLong(id));
    	} else {
    		return this.dbBroker.getDvd(id);
    	}
    }
    
	//////////////////////////////////////////////////////////////////////////
	// Cart operations
	//////////////////////////////////////////////////////////////////////////
    
    //
    // Get all rentals from the Shopping Cart.
    //
    public Rental[] getCartRentals() {
    	Rental[] cartDvdsList = (Rental[])this.cart.rentals.toArray(new Rental[] {});
    	return cartDvdsList;
    }
    
    //
    // Add a DVD to the Shopping Cart.
    //
    public boolean addToCart(Rental rental) {
    	if (this.dbBroker.reserveDvd(rental.getDvd()) == false)
    		return false;
    	return this.cart.addRental(rental);
    }
    
    //
    // Remove a DVD from the Shopping Cart.
    //
    public void removeFromCart(Dvd dvd) {
    	this.dbBroker.unreserveDvd(dvd);
    	this.cart.removeDvd(dvd);
    }
    
    //
    // Check out the entire Shopping Cart.
    //
    public boolean checkOut(Date dueDate) {
    	boolean boolResult;
    	if (this.cart.rentals.size() == 0)
    		return false;
    	
    	boolResult = this.dbBroker.checkOut(this.cart.rentals, dueDate);
    	if (boolResult == false)
    		return false;
    	this.cart.emptyCart();
    	return true;
    }
    
    //
    // Empty the Shopping Cart.
    //
    public void emptyCart() {
    	List<Rental> rentalList = this.cart.rentals;
    	Iterator<Rental> it = rentalList.iterator();
    	
    	if (rentalList == null || rentalList.size() == 0)
    		return;
    	
    	// Perform any undos in the database.
    	
    	while (it.hasNext()) {
    		Rental rental = (Rental)it.next();
    		this.dbBroker.unreserveDvd(rental.getDvd());
    	}
    	
    	this.cart.emptyCart();
    }
    
	//////////////////////////////////////////////////////////////////////////
	// User operations
	//////////////////////////////////////////////////////////////////////////
    
    public User getUser(int id) {
    	return this.dbBroker.getUser(id);
    }
    
    public User getUser(String username) {
    	return this.dbBroker.getUser(username);
    }
    
    public User[] getAllUsers() {
    	List<User> userList = this.dbBroker.getAllUsers();
    	User[] userArray = (User[])userList.toArray(new User[]{});
    	return userArray;
    }
    
	//////////////////////////////////////////////////////////////////////////
	// Rental operations
	//////////////////////////////////////////////////////////////////////////
    
    public Rental[] getRentalsByUser(User user) {
    	List<Rental> rentalList = this.dbBroker.getRentalsByUser(user);
    	if (rentalList.size() == 0)
    		return new Rental[] {};
    	Rental[] rentalArray = (Rental[])rentalList.toArray(new Rental[] {});
    	return rentalArray;
    }
    
    public Rental[] getOpenRentalsByUser(User user) {
    	List<Rental> rentalList = this.dbBroker.getRentalsByUser(user);
    	if (rentalList == null || rentalList.size() == 0)
    		return new Rental[] {};
    	DWDbg.log("Returning open rentals for user: " + user.toString());
    	Iterator<Rental> it = rentalList.iterator();
    	while (it.hasNext()) {
    		Rental rental = it.next();
    		if (rental.getEndDate() != null || rental.getRentalStarted() == true)
    			it.remove();
    		else
    			DWDbg.log(rental.toString());
    	}
    	Rental[] rentalArray = (Rental[])rentalList.toArray(new Rental[] {});
    	return rentalArray;
    }
    
    public Rental[] getCurrentRentalsByUser(User user) {
    	List<Rental> rentalList = this.dbBroker.getRentalsByUser(user);
    	if (rentalList == null || rentalList.size() == 0)
    		return new Rental[] {};
    	DWDbg.log("Returning current rentals for user: " + user.toString());
    	Iterator<Rental> it = rentalList.iterator();
    	while (it.hasNext()) {
    		Rental rental = it.next();
    		if (rental.getEndDate() != null || rental.getRentalStarted() == false)
    			it.remove();
    		else
    			DWDbg.log(rental.toString());
    	}
    	Rental[] rentalArray = (Rental[])rentalList.toArray(new Rental[] {});
    	return rentalArray;
    }
    
    public Rental[] getClosedRentalsByUser(User user) {
    	List<Rental> rentalList = this.dbBroker.getRentalsByUser(user);
    	if (rentalList == null || rentalList.size() == 0)
    		return new Rental[] {};
    	DWDbg.log("Returning closed rentals for user: " + user.toString());
    	Iterator<Rental> it = rentalList.iterator();
    	while (it.hasNext()) {
    		Rental rental = it.next();
    		if (rental.getEndDate() == null)
    			it.remove();
    		else
    			DWDbg.log(rental.toString());
    	}
    	Rental[] rentalArray = (Rental[])rentalList.toArray(new Rental[] {});
    	return rentalArray;
    }
    
    public Rental getRentalById(int rentalId) {
    	return this.dbBroker.getRentalById(rentalId);
    }
    
    public boolean removeRental(Rental rental) {
    	return this.dbBroker.removeRental(rental);
    }
    
    public boolean proceedAllRentals(User user) {
    	return this.dbBroker.proceedAllRentals(user);
    }
    
    public boolean endRent(Rental rental) {
    	return this.dbBroker.endRent(rental);
    }
}