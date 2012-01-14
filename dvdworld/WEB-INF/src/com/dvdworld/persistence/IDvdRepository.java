package com.dvdworld.persistence;

import java.util.List;

import com.dvdworld.model.Dvd;

public interface IDvdRepository {
	public List<Dvd> getAllDvds();
	public Dvd getDvd(int id);
	public boolean addDvd(Dvd dvd);
	public void removeDvd(Dvd dvd);
	public boolean reserveDvd(Dvd dvd);
	public boolean unreserveDvd(Dvd dvd);
}
