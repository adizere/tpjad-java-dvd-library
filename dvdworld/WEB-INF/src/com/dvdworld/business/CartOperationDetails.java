package com.dvdworld.business;

import java.util.Date;

import com.dvdworld.model.User;

//
// DVD rentals additions information that is passed from the web UI layer
// to the controller and further used for cart transactions.
//
public class CartOperationDetails {
	public Date dueDate;
	public User userLoggedIn;
}
