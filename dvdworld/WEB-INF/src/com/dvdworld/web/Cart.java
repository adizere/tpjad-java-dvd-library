package com.dvdworld.web;

import java.util.List;
import java.util.ArrayList;

import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;


//
// Cart used be user to fill with all sorts of Rentals.
//
public class Cart {
	public List<Rental> rentals = new ArrayList<Rental>();
	
	public Cart() {
	}
	
	//
	// Returns true if a rental has been added to the cart.
	// Returns false if the rental hasn't been added to the cart successfully.
	// Maybe the DVD already exists in the cart.
	//
	public boolean addRental(Rental rental) {
		if (containsDvd (rental.getDvd()))
			return false;
		return rentals.add(rental);
	}
	
	//
	// Check if a certain DVD has already been added to the cart.
	//
	public boolean containsDvd(Dvd dvd) {
		Rental currentRental = null;
		for (int i = 0; i < rentals.size(); i++) {
			currentRental = rentals.get(i);
			if (currentRental.getDvd().getId() == dvd.getId())
				return true;
		}
		return false;
	}
	
	//
	// Remove DVD(i.e. Rental) from cart.
	//
	public void removeDvd(Dvd dvd) {
		Rental currentRental = null;
		for (int i = 0; i < rentals.size(); i++) {
			currentRental = rentals.get(i);
			if (currentRental.getDvd().getId() == dvd.getId()) {
				rentals.remove(i);
				return;
			}
		}
	}
}