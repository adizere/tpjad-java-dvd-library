package com.dvdworld.services;

import java.util.Date;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.Assert;

import com.dvdworld.business.CartOperations;
import com.dvdworld.business.CartOperationDetails;
import com.dvdworld.dbg.DWDbg;
import com.dvdworld.model.Dvd;
import com.dvdworld.model.Rental;
import com.dvdworld.model.User;

public class DvdWorldServiceImpl implements DvdWorldService {
    private DvdWorldDao dvdWorldDao;
    

    // Not used unless you declare a <protect-pointcut>
    @Pointcut("execution(* com.dvdworld.services.DvdWorldServiceImpl.*(..))")
    public void myPointcut() {}

    public DvdWorldServiceImpl(DvdWorldDao dvdWorldDao) {
        Assert.notNull(dvdWorldDao);
        this.dvdWorldDao = dvdWorldDao;
    }

    public Dvd[] findDvds() {
        return this.dvdWorldDao.findDvds();
    }
    
    public Rental[] getCartRentals() {
    	return this.dvdWorldDao.getCartRentals();
    }

    /*
     * Like, this is another hint we can use!!
    public Dvd post(Dvd account, double amount) {
        Assert.notNull(account);

        // We read a DvdWorld account from DAO so it reflects the latest balance
        Account a = dvdWorldDao.readAccount(account.getId());
        if (account == null) {
            throw new IllegalArgumentException("Couldn't find requested account");
        }

        a.setBalance(a.getBalance() + amount);
        dvdWorldDao.createOrUpdateAccount(a);
        return a;
    }*/

    public Dvd readDvd(int id) {
        return dvdWorldDao.readDvd(id);
    }
    
    public User getUserByUsername(String username) {
    	return dvdWorldDao.getUser(username);
    }
    
    public boolean processCart(Dvd dvd, CartOperations operation, CartOperationDetails details)
    {
    	boolean success = true;
    	
    	// Check for specific DVD operations, that affect only a particular DVD.
    	if (operation == CartOperations.ADDTOCART || operation == CartOperations.REMOVEFROMCART) {
    		
    		Assert.notNull(dvd);
    		
	        // We read a DvdWorld account from DAO so it reflects the latest balance
	        Dvd a = dvdWorldDao.readDvd(dvd.getId());
	        if (dvd == null) {
	            throw new IllegalArgumentException("Couldn't find requested DVD.");
	        }
    	}
        
        switch (operation) {
        case ADDTOCART:
        	// Create a rental and add it to the cart.
        	Rental rental = new Rental();
        	rental.setDvd(dvd);
        	rental.setUser(details.userLoggedIn);
        	rental.setQuantity(1); // TODO: just one for now.
        	// Other fields like endDate, dueDate and startDate will be set during checkout.
        	dvdWorldDao.addToCart(rental);
        	break;
        case REMOVEFROMCART:
        	dvdWorldDao.removeFromCart(dvd);
        	break;
        case CHECKOUT:
        	success = dvdWorldDao.checkOut(details.dueDate);
        	break;
        case EMPTYCART:
        	dvdWorldDao.emptyCart();
        	break;
        }
        
        return success;
    }
}