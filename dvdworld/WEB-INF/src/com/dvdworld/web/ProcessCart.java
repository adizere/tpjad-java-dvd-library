package com.dvdworld.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dvdworld.model.Dvd;
import com.dvdworld.services.DvdWorldService;
import com.dvdworld.business.CartOperations;
import com.dvdworld.business.CartOperationDetails;
import com.dvdworld.business.DvdWorldBusinessUtils;

public class ProcessCart implements Controller {

    private DvdWorldService dvdWorldService;

    public ProcessCart(DvdWorldService dvdWorldService) {
        Assert.notNull(dvdWorldService);
        this.dvdWorldService = dvdWorldService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Security check (this is unnecessary if Spring Security is performing the authorization)
//        if (!request.isUserInRole("ROLE_TELLER")) {
//            throw new AccessDeniedException("You must be a teller to post transactions (Spring Security message)");
//        }

        // Actual business logic
    	int id = -1;
    	try {
    		id = ServletRequestUtils.getIntParameter(request, "id");
    	} catch (Exception e) {
    		// Current DVD ID is not important for this request.
    	}
        String operationString = ServletRequestUtils.getRequiredStringParameter(request, "operation");
        CartOperations operation = CartOperations.getEnum(operationString);
        // Other values follow here...
        String dueDateString = ServletRequestUtils.getStringParameter(request, "dueDate");
        CartOperationDetails details = new CartOperationDetails();
        details.dueDate = DvdWorldBusinessUtils.StringToDate(dueDateString);
        
        Dvd dvd = dvdWorldService.readDvd(id);
        dvdWorldService.processCart(dvd, operation, details);

        if (operation == CartOperations.CHECKOUT)
        	return new ModelAndView("redirect:checkOut.html");
        
        //return new ModelAndView("redirect:viewDvds.html");
        return new ModelAndView("redirect:myShoppingCart.html");
    }

}