package com.dvdworld.persistence;

import java.util.Date;
import java.util.List;

import com.dvdworld.model.Rental;
import com.dvdworld.model.User;

public interface IRentalRepository {
	public List<Rental> getAllRentals();
	public List<Rental> getRentalsByUser(User user);
	public Rental getRentalById(int rentalId);
	public boolean checkOut(List<Rental> rentals, Date dueDate);
	public boolean removeRental(Rental rental);
	public boolean proceedAllRentals(User user);
	public boolean endRent(Rental rental);
}
