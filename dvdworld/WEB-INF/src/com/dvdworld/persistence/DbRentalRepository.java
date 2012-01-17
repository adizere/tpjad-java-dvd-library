package com.dvdworld.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.dvdworld.db.DvdWorldDbBroker;
import com.dvdworld.dbg.DWDbg;
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
				
				if (!dvdResultSet.next())
					throw new Exception("Error: retrieved empty rented DVD from database.");
				
				PreparedStatement userPreStmt =
						connection.prepareStatement("SELECT * FROM users WHERE id=?");
				userPreStmt.setInt(1, resultSet.getInt("userid"));
				ResultSet userResultSet = userPreStmt.executeQuery();
				
				if (!dvdResultSet.next())
					throw new Exception("Error: retrieved empty user (borrower) from database.");
				
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
				rental.setRentalStarted(resultSet.getBoolean("rentalstarted"));

				java.sql.Date someDate = null;
				someDate = resultSet.getDate("startdate");
				if (someDate != null)
					rental.setStartDate(new java.util.Date(someDate.getTime()));
				else
					rental.setStartDate(null);
				
				someDate = resultSet.getDate("duedate");
				if (someDate != null)
					rental.setDueDate(new java.util.Date(someDate.getTime()));
				else
					rental.setDueDate(null);
				
				someDate = resultSet.getDate("enddate");
				if (someDate != null)
					rental.setEndDate(new java.util.Date(someDate.getTime()));
				else
					rental.setEndDate(null);
				
				rentalList.add(rental);
			}
		} catch (Exception e) {
			DWDbg.log("Error getting all Rentals: " + e);
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				DWDbg.log(stackTrace[i].toString());
			}
			this.dbBroker.closeConnection(connection);
			return null;
		}
		this.dbBroker.closeConnection(connection);
		return rentalList;
	}
	
	public List<Rental> getRentalsByUser(User user) {
		Connection connection = null;
		List<Rental> rentalList = new ArrayList<Rental>();
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement rentalPreStmt =
				connection.prepareStatement("SELECT * FROM rentals WHERE userid=?");
			rentalPreStmt.setInt(1, user.getId());
			ResultSet resultSet = rentalPreStmt.executeQuery();
			
			while (resultSet.next()) {
				PreparedStatement dvdPreStmt =
					connection.prepareStatement("SELECT * FROM dvds WHERE id=?");
				dvdPreStmt.setInt(1, resultSet.getInt("dvdid"));
				ResultSet dvdResultSet = dvdPreStmt.executeQuery();
				
				if (!dvdResultSet.next())
					throw new Exception("Error: retrieved empty rented DVD from database.");
				
				Rental rental = new Rental();
				rental.setId(resultSet.getInt("id"));
				rental.setDvd(new Dvd(
						dvdResultSet.getInt("id"),
						dvdResultSet.getString("title"),
						dvdResultSet.getString("description"),
						dvdResultSet.getInt("quantity"),
						dvdResultSet.getInt("price")));
				rental.setUser(user);
				rental.setQuantity(resultSet.getInt("quantity"));
				rental.setRentalStarted(resultSet.getBoolean("rentalstarted"));
				
				java.sql.Date someDate = null;
				someDate = resultSet.getDate("startdate");
				if (someDate != null)
					rental.setStartDate(new java.util.Date(someDate.getTime()));
				else
					rental.setStartDate(null);
				
				someDate = resultSet.getDate("duedate");
				if (someDate != null)
					rental.setDueDate(new java.util.Date(someDate.getTime()));
				else
					rental.setDueDate(null);
				
				someDate = resultSet.getDate("enddate");
				if (someDate != null)
					rental.setEndDate(new java.util.Date(someDate.getTime()));
				else
					rental.setEndDate(null);
				
				rentalList.add(rental);
			}
		} catch (Exception e) {
			DWDbg.log("Error getting all Rentals: " + e);
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				DWDbg.log(stackTrace[i].toString());
			}
			this.dbBroker.closeConnection(connection);
			return null;
		}
		
		this.dbBroker.closeConnection(connection);
		return rentalList;
	}
	
	//
	// Get a Rental by Rental ID.
	//
	public Rental getRentalById(int rentalId) {
		Connection connection = null;
		Rental rental = null;
		
		DWDbg.log("Retrieving Rental by ID: " + rentalId);
		
		try {
			connection = this.dbBroker.getConnection();
			
			PreparedStatement rentalPreStmt =
				connection.prepareStatement("SELECT * FROM rentals WHERE id=?");
			rentalPreStmt.setInt(1, rentalId);
			ResultSet rentalResultSet = rentalPreStmt.executeQuery();
			
			if (!rentalResultSet.next())
				throw new Exception("Error getting rental.");
				
			PreparedStatement dvdPreStmt =
					connection.prepareStatement("SELECT * FROM dvds WHERE id=?");
			dvdPreStmt.setInt(1, rentalResultSet.getInt("dvdid"));
			ResultSet dvdResultSet = dvdPreStmt.executeQuery();
			
			if (!dvdResultSet.next())
				throw new Exception("Error getting dvd.");
			
			Dvd rentedDvd = new Dvd(
					dvdResultSet.getInt("id"),
					dvdResultSet.getString("title"),
					dvdResultSet.getString("description"),
					dvdResultSet.getInt("quantity"),
					dvdResultSet.getInt("price"));
			
			PreparedStatement userPreStmt =
					connection.prepareStatement("SELECT id, username, name FROM users WHERE id=?");
			userPreStmt.setInt(1, rentalResultSet.getInt("userid"));
			ResultSet userResultSet = userPreStmt.executeQuery();
			
			if (!userResultSet.next())
				throw new Exception("Error getting user.");
			
			rental = new Rental();
			rental.setId(rentalResultSet.getInt("id"));
			rental.setDvd(rentedDvd);
			rental.setUser(new User(
					userResultSet.getInt("id"),
					userResultSet.getString("username"),
					userResultSet.getString("name"),
					null));
			rental.setQuantity(rentalResultSet.getInt("quantity"));
			rental.setRentalStarted(rentalResultSet.getBoolean("rentalstarted"));

			java.sql.Date someDate = null;
			someDate = rentalResultSet.getDate("startdate");
			if (someDate != null)
				rental.setStartDate(new java.util.Date(someDate.getTime()));
			else
				rental.setStartDate(null);
			
			someDate = rentalResultSet.getDate("duedate");
			if (someDate != null)
				rental.setDueDate(new java.util.Date(someDate.getTime()));
			else
				rental.setDueDate(null);
			
			someDate = rentalResultSet.getDate("enddate");
			if (someDate != null)
				rental.setEndDate(new java.util.Date(someDate.getTime()));
			else
				rental.setEndDate(null);
		} catch (Exception e) {
			DWDbg.log("Error getting all Rentals: " + e);
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (int i = 0; i < stackTrace.length; i++) {
				DWDbg.log(stackTrace[i].toString());
			}
			this.dbBroker.closeConnection(connection);
			return null;
		}
		
		this.dbBroker.closeConnection(connection);
		return rental;		
	}
	
	//
	// Check out Rentals received from the Shopping Cart
	// and add Rental records to the database.
	//
	public boolean checkOut(List<Rental> rentals, Date dueDate) {
		Connection connection = null;
		
		if (rentals.size() == 0)
			return false;
		
		Iterator<Rental> it = rentals.iterator();
		while (it.hasNext()) {
			Rental rental = (Rental)it.next();
			rental.setStartDate(new Date());
			rental.setDueDate(dueDate);
		}
		
		try {
			connection = this.dbBroker.getConnection();
			
			for (int i = 0; i < rentals.size(); i++) {
				Rental rental = rentals.get(i);
				// Remember we set the endDate to NULL, because we don't actually know
				// when the Borrower is going to bring the DVD back.
				PreparedStatement insertStmt =
					connection.prepareStatement("INSERT INTO rentals values (?, ?, ?, ?, ?, ?, NULL, ?)");
				insertStmt.setInt(1, 0);
				insertStmt.setInt(2, rental.getDvd().getId());
				insertStmt.setInt(3, rental.getUser().getId());
				insertStmt.setInt(4, rental.getQuantity());
				insertStmt.setDate(5, new java.sql.Date(rental.getStartDate().getTime()));
				insertStmt.setDate(6, new java.sql.Date(rental.getDueDate().getTime()));
				insertStmt.setBoolean(7, false);
				insertStmt.execute();
			}
		} catch (Exception e) {
			DWDbg.log("Error adding checking out: " + e);
			DWDbg.logStack(e);
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}
	
	public boolean removeRental(Rental rental) {
		Connection connection = null;
		
		try {
			connection = this.dbBroker.getConnection();
			
			PreparedStatement removeStmt =
				connection.prepareStatement("DELETE FROM rentals WHERE id=? LIMIT 1");
			removeStmt.setInt(1, rental.getId());
			removeStmt.execute();
			
		} catch (Exception e) {
			DWDbg.log("Error removing rental: " + e);
			DWDbg.logStack(e);
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}

	public boolean proceedAllRentals(User user) {
		Connection connection = null;
		
		try {
			connection = this.dbBroker.getConnection();
			
			PreparedStatement removeStmt =
				connection.prepareStatement("UPDATE rentals SET rentalstarted=1 WHERE userid=?");
			removeStmt.setInt(1, user.getId());
			removeStmt.execute();
			
		} catch (Exception e) {
			DWDbg.log("Error proceeding all rentals from user: " + e);
			DWDbg.logStack(e);
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}

	public boolean endRent(Rental rental) {
		Connection connection = null;
		
		try {
			connection = this.dbBroker.getConnection();
			
			PreparedStatement removeStmt =
				connection.prepareStatement("UPDATE rentals SET enddate=CURDATE() WHERE id=?");
			removeStmt.setInt(1, rental.getId());
			removeStmt.execute();
			
		} catch (Exception e) {
			DWDbg.log("Error ending renting: " + e);
			DWDbg.logStack(e);
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}
}