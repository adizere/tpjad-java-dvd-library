package com.dvdworld.services;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.Assert;

import com.dvdworld.business.CartOperations;
import com.dvdworld.model.Dvd;

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
    
    
    public void processCart(Dvd dvd, CartOperations operation)
    {
    	Assert.notNull(dvd);
    	
    	// Check for specific DVD operations, that affect only a particular DVD.
    	if (operation == CartOperations.ADDTOCART || operation == CartOperations.REMOVEFROMCART) {
	        // We read a DvdWorld account from DAO so it reflects the latest balance
	        Dvd a = dvdWorldDao.readDvd(dvd.getId());
	        if (dvd == null) {
	            throw new IllegalArgumentException("Couldn't find requested DVD.");
	        }
    	}
        
        switch (operation) {
        case ADDTOCART:
        	dvdWorldDao.addToCart(dvd);
        	break;
        case REMOVEFROMCART:
        	dvdWorldDao.removeFromCart(dvd);
        	break;
        case CHECKOUT:
        	dvdWorldDao.checkOut();
        	break;
        case EMPTYCART:
        	dvdWorldDao.emptyCart();
        	break;
        }
        
        return;
    }
}