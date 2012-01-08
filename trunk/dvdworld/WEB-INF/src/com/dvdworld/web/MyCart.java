package com.dvdworld.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dvdworld.services.DvdWorldService;

//
// Provides a list with the DVDs the user currently selected for rent.
//
public class MyCart implements Controller {

    private DvdWorldService dvdWorldService;

    public MyCart(DvdWorldService dvdWorldService) {
        Assert.notNull(dvdWorldService);
        this.dvdWorldService = dvdWorldService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Security check (this is unnecessary if Spring Security is performing the authorization)
//        if (request.getUserPrincipal() == null) {
//            throw new AuthenticationCredentialsNotFoundException("You must login to view the dvd list (Spring Security message)"); // only for Spring Security managed authentication
//        }

        // Actual business logic
        ModelAndView mav = new ModelAndView("myShoppingCart");
        mav.addObject("cartRentals", dvdWorldService.getCartRentals());
        return mav;
    }

}