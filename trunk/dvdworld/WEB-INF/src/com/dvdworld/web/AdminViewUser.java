package com.dvdworld.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dvdworld.business.CartOperationDetails;
import com.dvdworld.business.CartOperations;
import com.dvdworld.business.DvdWorldBusinessUtils;
import com.dvdworld.dbg.DWDbg;
import com.dvdworld.model.User;
import com.dvdworld.model.Rental;

import com.dvdworld.services.DvdWorldService;

public class AdminViewUser implements Controller {

    private DvdWorldService dvdWorldService;

    public AdminViewUser(DvdWorldService dvdWorldService) {
        Assert.notNull(dvdWorldService);
        this.dvdWorldService = dvdWorldService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("adminViewUser");
    	int userId = -1;
    	User user = null;
    	CartOperations operation = CartOperations.INVALIDOP;
    	
    	try {
    		try {
    			String operationString = ServletRequestUtils.getRequiredStringParameter(request, "operation");
    			operation = CartOperations.getEnum(operationString);
    		} catch (Exception e) {
    			operation = CartOperations.INVALIDOP;
    		}
    			
    		try {
        		userId = ServletRequestUtils.getIntParameter(request, "userid");
        		user = dvdWorldService.getUserById(userId);
        		if (user == null)
        			throw new Exception("No such user with id=" + userId);
        	} catch (Exception e) {
        		DWDbg.log("Error getting user information needed to retrieve user rentals: " + e);
        		return mav;
        	}
    		
    		switch (operation)
    		{
    		case REMOVEFROMCART:
    			{
    				try {
    					int rentalId = ServletRequestUtils.getIntParameter(request, "rentalid");
    					Rental rental = dvdWorldService.getRentalById(rentalId);
    					dvdWorldService.restoreRental(rental);
    				} catch (Exception e) {
    					DWDbg.log("Error getting rentalid for Rental cart operation: " + e);
    					DWDbg.logStack(e);
    				}
    			}
    			break;
    		case PROCEEDRENT:
    			{
    				try {
    					dvdWorldService.proceedAllRentals(user);
    				} catch (Exception e) {
    					DWDbg.log("Error proceeding rental operation: " + e);
    					DWDbg.logStack(e);
    				}
    			}
    			break;
    		case ENDRENT:
				{
					try {
    					int rentalId = ServletRequestUtils.getIntParameter(request, "rentalid");
    					Rental rental = dvdWorldService.getRentalById(rentalId);
    					dvdWorldService.endRent(rental);
					} catch (Exception e) {
						DWDbg.log("Error proceeding rental operation: " + e);
						DWDbg.logStack(e);
					}
				}
    			break;
    		case INVALIDOP:
    			break;
        	default:
        		break;
    		}
    		
    		Rental[] openRentals = dvdWorldService.getOpenRentalsByUser(user);
    		Rental[] currentRentals = dvdWorldService.getCurrentRentalsByUser(user);
    		Rental[] closedRentals = dvdWorldService.getClosedRentalsByUser(user);
    		
    		double totalSumToPay = DvdWorldBusinessUtils.getTotalSumForRentals(openRentals);
    		
    		if (openRentals.length > 0) {
    			mav.addObject("totalSumToPay", totalSumToPay);
    		}
    		
    		mav.addObject("userOpenRentals", openRentals);
    		mav.addObject("userCurrentRentals", currentRentals);
        	mav.addObject("userClosedRentals", closedRentals);
            return mav;
            
    	} catch (Exception e) {
    		DWDbg.log("Error getting operation type for AdminViewUser Controller: " + e);
    		DWDbg.logStack(e);
    		return mav;
    	}
    }

}