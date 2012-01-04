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
        Long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        String operationString = ServletRequestUtils.getRequiredStringParameter(request, "operation");
        CartOperations operation = CartOperations.getEnum(operationString);
        Dvd a = dvdWorldService.readDvd(id);
        dvdWorldService.processDvd(a, operation);

        return new ModelAndView("redirect:viewDvds.html");
    }

}