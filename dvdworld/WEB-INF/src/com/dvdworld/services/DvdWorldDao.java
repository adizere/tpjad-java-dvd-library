package com.dvdworld.services;

import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;

public interface DvdWorldDao {
    public Dvd readDvd(int i);
    public void createOrUpdateDvd(Dvd dvd);
    public Dvd[] findDvds();
    public Rental[] getCartRentals();
    
    // Cart operations
    public boolean addToCart(Rental rental);
    public void removeFromCart(Dvd dvd);
    public void checkOut();
    public void emptyCart();
}