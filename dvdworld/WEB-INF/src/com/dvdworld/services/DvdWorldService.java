package com.dvdworld.services;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;
import com.dvdworld.business.CartOperations;
import com.dvdworld.business.CartOperationDetails;

public interface DvdWorldService {

    public Dvd readDvd(int id);

    public Dvd[] findDvds();
    
    //@PreAuthorize("hasRole('ROLE_TELLER')")
    public Rental[] getCartRentals();
    
    // TODO: Just a hint of what we can do further:
    //@PreAuthorize(
    //        "hasRole('ROLE_SUPERVISOR') or " +
    //        "hasRole('ROLE_TELLER') and (#account.balance + #amount >= -#account.overdraft)" )
    //public Dvd post(Dvd dvd, double amount);
    
    //@PreAuthorize("hasRole('ROLE_TELLER')")
    public void processCart(Dvd dvd, CartOperations operation, CartOperationDetails details);
}