package com.dvdworld.services;

import java.util.HashMap;
import java.util.Map;

import com.dvdworld.model.Dvd;

public class DvdWorldDaoStub implements DvdWorldDao {
    private long id = 0;
    private Map<Long, Dvd> dvds = new HashMap<Long, Dvd>();

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
    // Get a specific DVD.
    //
    public Dvd readDvd(int id) {
        return dvds.get(id);
    }
    
    //
    // Add a DVD to the Shopping Cart.
    //
    public void addToCart(Dvd dvd) {
    	
    }
    
    //
    // Remove a DVD from the Shopping Cart.
    //
    public void removeFromCart(Dvd dvd) {
    	
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