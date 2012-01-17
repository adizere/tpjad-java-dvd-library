package com.dvdworld.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;
import com.dvdworld.model.User;
import com.dvdworld.infrastructure.DvdWorldConfigInfo;
import com.dvdworld.persistence.DbDvdRepository;
import com.dvdworld.persistence.DbUserRepository;
import com.dvdworld.persistence.DbRentalRepository;
import com.dvdworld.persistence.IDvdRepository;
import com.dvdworld.persistence.IUserRepository;
import com.dvdworld.persistence.IRentalRepository;

//
// Main class that handles database communication.
//
public class DvdWorldDbBroker {
	
	private Connection connection;
	
	public DvdWorldConfigInfo configInfo = null;
	
	private IDvdRepository dvdRepository = null;
	private IRentalRepository rentalRepository = null;
	private IUserRepository userRepository = null;
	
	public DvdWorldDbBroker(DvdWorldConfigInfo configInfo) {
		this.configInfo = configInfo;
		this.dvdRepository = new DbDvdRepository(this);
		this.rentalRepository = new DbRentalRepository(this);
		this.userRepository = new DbUserRepository(this);
	}
		
	public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed())
            	this.connection = getNewConnection();
        } catch (SQLException e) {
            System.out.println("Database error. Unable to create connection: " + e);
        }
        return this.connection;
	}
	
	public String hello() {
		return "hello from DvdWorldDbBroker";
	}
	
	private Connection getNewConnection() {
		Connection newConnection = null;
		try
		{
			String driver = configInfo.dbDriver;
			String url = configInfo.dbConnectionString;
			String user = configInfo.dbUser;
			String pass = configInfo.dbPassword;
		    Class.forName(driver);
		    newConnection = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
		    System.out.println("Error loading driver: " + e);
		} catch (SQLException e) {
		    System.out.println("SQL Error getting connection: " + e);
		} catch (Exception e) {
			System.out.println("Error getting connection: " + e);
		}
		return newConnection;
	}
	
	public void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////
	// DVD operations
	//////////////////////////////////////////////////////////////////////////
	
	public List<Dvd> getAllDvds() {
		return this.dvdRepository.getAllDvds();
	}
	
	//
	// When a User adds a DVD to his Shopping Cart, the DVD will be reserved.
	// This means, that the available quantity for that DVD will be decremented.
	//
	public boolean reserveDvd(Dvd dvd) {
		return this.dvdRepository.reserveDvd(dvd);
	}
	
	//
	// Do the opposite of reserveDvd().
	//
	public boolean unreserveDvd(Dvd dvd) {
		return this.dvdRepository.unreserveDvd(dvd);
	}
	
	public Dvd getDvd(int id) {
		return this.dvdRepository.getDvd(id);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// Rental operations
	//////////////////////////////////////////////////////////////////////////
	
	public boolean checkOut(List<Rental> rentals, Date dueDate) {
		return this.rentalRepository.checkOut(rentals, dueDate);
	}
	
	
	public List<Rental> getRentalsByUser(User user) {
		return this.rentalRepository.getRentalsByUser(user);
	}
	
	public Rental getRentalById(int rentalId) {
		return this.rentalRepository.getRentalById(rentalId);
	}
	
	public boolean removeRental(Rental rental) {
		return this.rentalRepository.removeRental(rental);
	}
	
	public boolean proceedAllRentals(User user) {
		return this.rentalRepository.proceedAllRentals(user);
	}
	
	public boolean endRent(Rental rental) {
		return this.rentalRepository.endRent(rental);
	}
	
	//////////////////////////////////////////////////////////////////////////
	// User operations
	//////////////////////////////////////////////////////////////////////////
	
	public User getUser(int id) {
		return this.userRepository.getUser(id);
	}
	
	public User getUser(String username) {
		return this.userRepository.getUser(username);
	}
	
	public List<User> getAllUsers() {
		return this.userRepository.getAllUsers();
	}
}