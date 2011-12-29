package com.dvdlibrary.business;

import java.util.List;
import java.util.ArrayList;
import com.dvdlibrary.model.*;

//
// This is the class that handles DVDs
// between the UI controller and the persistence layer.
//
public class MyDvdLibrary {
	
	public MyDvdLibrary () {
	}
	
	public List<Dvd> getAllDvds() {
		List<Dvd> dvdItems = new ArrayList<Dvd>();
		Dvd dvd1 = new Dvd();
		Dvd dvd2 = new Dvd();
		
		dvd1.setTitle("Title1");
		dvd2.setTitle("Title2");
		
		dvdItems.add(dvd1);
		dvdItems.add(dvd2);
		
		return dvdItems;
	}
}