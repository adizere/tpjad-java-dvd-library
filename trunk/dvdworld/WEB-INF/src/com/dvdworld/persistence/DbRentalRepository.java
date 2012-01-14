package com.dvdworld.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dvdworld.db.DvdWorldDbBroker;
import com.dvdworld.persistence.IRentalRepository;
import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;
import com.dvdworld.model.User;

public class DbRentalRepository implements IRentalRepository {
	
	private DvdWorldDbBroker dbBroker = null;
	
	public DbRentalRepository(DvdWorldDbBroker dbBroker) {
		this.dbBroker = dbBroker;
	}
	
	public List<Rental> getAllRentals() {
		Connection connection = null;
		List<Rental> rentalList = new ArrayList<Rental>();
		
		try {
			connection = this.dbBroker.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM rentals");
			
			while (resultSet.next()) {
				PreparedStatement dvdPreStmt =
					connection.prepareStatement("SELECT * FROM dvds WHERE id=?");
				dvdPreStmt.setInt(1, resultSet.getInt("dvdid"));
				ResultSet dvdResultSet = dvdPreStmt.executeQuery();
				
				PreparedStatement userPreStmt =
						connection.prepareStatement("SELECT * FROM users WHERE id=?");
				userPreStmt.setInt(1, resultSet.getInt("userid"));
				ResultSet userResultSet = userPreStmt.executeQuery();
				
				Rental rental = new Rental();
				rental.setId(resultSet.getInt("id"));
				rental.setDvd(new Dvd(
						dvdResultSet.getInt("id"),
						dvdResultSet.getString("title"),
						dvdResultSet.getString("description"),
						dvdResultSet.getInt("quantity"),
						dvdResultSet.getInt("price")));
				rental.setUser(new User(
						userResultSet.getInt("id"),
						userResultSet.getString("username"),
						userResultSet.getString("name"),
						null));
				rental.setQuantity(resultSet.getInt("quantity"));
				rental.setStartDate(new java.util.Date(resultSet.getDate("startdate").getTime()));
				rental.setDueDate(new java.util.Date(resultSet.getDate("duedate").getTime()));
				rental.setEndDate(new java.util.Date(resultSet.getDate("enddate").getTime()));
				
				rentalList.add(rental);
			}
		} catch (Exception e) {
			System.out.println("Error getting all DVDs: " + e);
			this.dbBroker.closeConnection(connection);
			return null;
		}
		this.dbBroker.closeConnection(connection);
		return rentalList;
	}
	
	//
	// Check out Rentals received from the Shopping Cart
	// and add Rental records to the database.
	//
	public boolean checkOut(List<Rental> rentals) {
		Connection connection = null;
		
		if (rentals.size() == 0)
			return false;
		
		try {
			connection = this.dbBroker.getConnection();
			
			for (int i = 0; i < rentals.size(); i++) {
				Rental rental = rentals.get(i);
				PreparedStatement insertStmt =
					connection.prepareStatement("INSERT INTO rentals values (?, ?, ?, ?, ?, ?, ?)");
				insertStmt.setInt(1, 0);
				insertStmt.setInt(2, rental.getDvd().getId());
				insertStmt.setInt(3, rental.getUser().getId());
				insertStmt.setInt(4, rental.getQuantity());
				insertStmt.setDate(5, new java.sql.Date(rental.getStartDate().getTime()));
				insertStmt.setDate(6, new java.sql.Date(rental.getDueDate().getTime()));
				insertStmt.setDate(7, new java.sql.Date(rental.getEndDate().getTime()));
				insertStmt.execute();
			}
		} catch (Exception e) {
			System.out.println("Error adding new DVD: " + e);
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}
}