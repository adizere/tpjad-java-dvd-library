package com.dvdworld.services;

import java.util.HashMap;
import java.util.Map;

import com.dvdworld.business.DvdWorldBusinessUtils;
import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;
import com.dvdworld.web.Cart;

public class DvdWorldDaoStub implements DvdWorldDao {
    private long id = 0;
    private Map<Long, Dvd> dvds = new HashMap<Long, Dvd>();
    private Cart cart = new Cart();

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
        Dvd[] dvdList = dvds.values().toArray(new Dvd[] {});
        System.out.println("Returning " + dvdList.length + " dvd(s):");
        for (int i = 0; i < dvdList.length; i++) {
            System.out.println(" > " + dvdList[i]);
        }
        return dvdList;
    }
    
    //
    // Get all rentals from the Shopping Cart.
    //
    public Rental[] getCartRentals() {
    	Rental[] cartDvdsList = (Rental[])cart.rentals.toArray(new Rental[] {});
    	return cartDvdsList;
    }
    
    //
    // Get a specific DVD.
    //
    public Dvd readDvd(int id) {
        return dvds.get(DvdWorldBusinessUtils.intToLong(id));
    }
    
    //
    // Add a DVD to the Shopping Cart.
    //
    public boolean addToCart(Rental rental) {
    	return cart.addRental(rental);
    }
    
    //
    // Remove a DVD from the Shopping Cart.
    //
    public void removeFromCart(Dvd dvd) {
    	cart.removeDvd(dvd);
    }
    
    //
    // Check out the entire Shopping Cart.
    //
    public void checkOut() {
    	
    }
    
    //
    // Empty the Shopping Cart.
    //
    public void emptyCart() {
    	
    }
}