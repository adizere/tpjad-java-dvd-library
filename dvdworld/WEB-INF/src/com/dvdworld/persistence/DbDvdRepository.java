package com.dvdworld.persistence;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.dvdworld.model.Dvd;
import com.dvdworld.db.DvdWorldDbBroker;
import com.dvdworld.persistence.IDvdRepository;

public class DbDvdRepository implements IDvdRepository {
	
	private DvdWorldDbBroker dbBroker = null;
	
	public DbDvdRepository(DvdWorldDbBroker dbBroker) {
		this.dbBroker = dbBroker;
	}
	
	public List<Dvd> getAllDvds() {
		Connection connection = null;
		List<Dvd> dvdList = new ArrayList<Dvd>();
		
		try {
			connection = this.dbBroker.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT id, title, description, quantity, price FROM dvds");
			while (resultSet.next()) {
				dvdList.add(
					new Dvd(resultSet.getInt("id"),
							resultSet.getString("title"),
							resultSet.getString("description"),
							resultSet.getInt("quantity"),
							resultSet.getDouble("price")));
			}
		} catch (Exception e) {
			System.out.println("Error getting all DVDs: " + e);
			this.dbBroker.closeConnection(connection);
			return null;
		}
		this.dbBroker.closeConnection(connection);
		return dvdList;
	}
	
	public Dvd getDvd(int id) {
		Connection connection = null;
		Dvd dvd = null;
		
		if (id <= 0)
			return null;
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement preStmt =
				connection.prepareStatement("SELECT id, title, description, quantity, price FROM dvds WHERE id=?");
			preStmt.setInt(1, id);
			ResultSet resultSet = preStmt.executeQuery();
			if (resultSet.next()) {
				dvd = new Dvd(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("description"),
						resultSet.getInt("quantity"),
						resultSet.getDouble("price"));
			}
		} catch (Exception e) {
			this.dbBroker.closeConnection(connection);
			return null;
		}
		
		this.dbBroker.closeConnection(connection);
		return dvd;
	}
	
	public boolean addDvd(Dvd dvd) {
		Connection connection = null;
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement insertStmt =
				connection.prepareStatement("INSERT INTO dvds values (?, ?, ?, ?, ?)");
			insertStmt.setInt(1, 0);
			insertStmt.setString(2, dvd.getTitle());
			insertStmt.setString(3, dvd.getDescription());
			insertStmt.setInt(4, dvd.getQuantity());
			insertStmt.setDouble(5, dvd.getPrice());
			insertStmt.execute();
		} catch (Exception e) {
			System.out.println("Error adding new DVD: " + e);
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}
	
	public void removeDvd(Dvd dvd) {
	}
	
	public boolean reserveDvd(Dvd dvd) {
		Connection connection = null;
		Dvd dbDvd = null;
		
		if (dvd == null)
			return false;
		
		dbDvd = getDvd(dvd.getId());
		if (dbDvd == null)
			return false;
		if (dbDvd.getQuantity() <= 0)
			return false;
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement preStmt =
				connection.prepareStatement("UPDATE dvds set quantity=quantity-1 where id=?");
			preStmt.setInt(1, dvd.getId());
			preStmt.execute();
		} catch (Exception e) {
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}
	
	public boolean unreserveDvd(Dvd dvd) {
		Connection connection = null;
		Dvd dbDvd = null;
		
		if (dvd == null)
			return false;
		
		dbDvd = getDvd(dvd.getId());
		if (dbDvd == null)
			return false;
		if (dbDvd.getQuantity() <= 0)
			return false;
		
		try {
			connection = this.dbBroker.getConnection();
			PreparedStatement preStmt =
				connection.prepareStatement("UPDATE dvds set quantity=quantity+1 where id=?");
			preStmt.setInt(1, dvd.getId());
			preStmt.execute();
		} catch (Exception e) {
			this.dbBroker.closeConnection(connection);
			return false;
		}
		
		this.dbBroker.closeConnection(connection);
		return true;
	}
}
