package com.dvdworld.persistence;

import java.util.Date;
import java.util.List;

import com.dvdworld.model.Rental;

public interface IRentalRepository {
	public List<Rental> getAllRentals();
	public boolean checkOut(List<Rental> rentals, Date dueDate);
}
