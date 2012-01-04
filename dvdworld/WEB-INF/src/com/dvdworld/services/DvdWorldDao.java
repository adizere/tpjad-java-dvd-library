package com.dvdworld.services;

import com.dvdworld.model.Dvd;;

public interface DvdWorldDao {
    public Dvd readDvd(int i);
    public void createOrUpdateDvd(Dvd dvd);
    public Dvd[] findDvds();
    
    // Cart operations
    public void addToCart(Dvd dvd);
    public void removeFromCart(Dvd dvd);
    public void checkOut();
    public void emptyCart();
}