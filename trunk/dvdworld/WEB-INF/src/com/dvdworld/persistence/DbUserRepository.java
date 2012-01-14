package com.dvdworld.persistence;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.dvdworld.model.User;
import com.dvdworld.db.DvdWorldDbBroker;
import com.dvdworld.persistence.IUserRepository;

public class DbUserRepository implements IUserRepository {
	
	private DvdWorldDbBroker dbBroker = null;
	
	public DbUserRepository(DvdWorldDbBroker dbBroker) {
		this.dbBroker = dbBroker;
	}
	
	public List<User> getAllUsers() {
		Connection connection = null;
		List<User> userList = null;
		
		try {
			connection = this.dbBroker.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id, username, name FROM users");
			userList = new ArrayList<User>();
			while (resultSet.next()) {
				userList.add(
					new User(
						resultSet.getInt("id"),
						resultSet.getString("username"),
						resultSet.getString("name"),
						null));
			}
		} catch (Exception e) {
			System.out.println("Error getting all Users: " + e);
			this.dbBroker.closeConnection(connection);
			return null;
		}
		
		this.dbBroker.closeConnection(connection);
		return userList;
	}
	
	public User getUser(int id) {
		Connection connection = null;
		User user = null;
		
		if (id <= 0)
			return null;
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement preStmt =
				connection.prepareStatement("SELECT id, username, name FROM users WHERE id=?");
			preStmt.setInt(1, id);
			ResultSet resultSet = preStmt.executeQuery();
			if (resultSet.next()) {
				user = new User(
						resultSet.getInt("id"),
						resultSet.getString("username"),
						resultSet.getString("name"),
						null);
			}
		} catch (Exception e) {
			this.dbBroker.closeConnection(connection);
			return null;
		}
		
		this.dbBroker.closeConnection(connection);
		return user;
	}
	
	public User getUser(String username) {
		Connection connection = null;
		User user = null;
		
		if (username == null || username.length() == 0)
			return null;
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement preStmt =
				connection.prepareStatement("SELECT id, username, name FROM users WHERE username=?");
			preStmt.setString(1, username);
			ResultSet resultSet = preStmt.executeQuery();
			if (resultSet.next()) {
				user = new User(
						resultSet.getInt("id"),
						resultSet.getString("username"),
						resultSet.getString("name"),
						null);
			}
		} catch (Exception e) {
			this.dbBroker.closeConnection(connection);
			return null;
		}
		
		this.dbBroker.closeConnection(connection);
		return user;
	}
}