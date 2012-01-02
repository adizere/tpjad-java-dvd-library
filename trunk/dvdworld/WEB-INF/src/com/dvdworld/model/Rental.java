package com.dvdworld.model;

import java.util.Date;

public class Rental {
	int id;
	Dvd dvdId;
	User borrowerId;
	int quantity;
	// When was the DVD borrowed?
	Date startDate;
	// When must be the DVD brought back?
	Date dueDate;
	// What was the DVD actually brought back?
	Date endDate;
	// How much does the borrower pay for this dvd?
	double cost;
}
