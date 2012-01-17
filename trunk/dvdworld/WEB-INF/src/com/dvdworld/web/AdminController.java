package com.dvdworld.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dvdworld.services.DvdWorldService;

public class AdminController implements Controller {

    private DvdWorldService dvdWorldService;

    public AdminController(DvdWorldService dvdWorldService) {
        Assert.notNull(dvdWorldService);
        this.dvdWorldService = dvdWorldService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("adminViewRentals");
        mav.addObject("users", dvdWorldService.getAllUsers());
        return mav;
    }

}