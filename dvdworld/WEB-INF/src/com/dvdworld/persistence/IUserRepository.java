package com.dvdworld.persistence;

import java.util.List;

import com.dvdworld.model.User;

public interface IUserRepository {
	public List<User> getAllUsers();
	public User getUser(int id);
	public User getUser(String username);
}
