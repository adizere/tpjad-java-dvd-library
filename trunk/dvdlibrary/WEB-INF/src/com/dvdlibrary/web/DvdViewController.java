package com.dvdlibrary.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.neurotech.quotes.Quote;
import net.neurotech.quotes.QuoteException;
import net.neurotech.quotes.QuoteFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dvdlibrary.business.MyDvdLibrary;
import com.dvdlibrary.model.*;

public class DvdViewController implements Controller {

    private com.dvdlibrary.business.MyDvdLibrary myDvdLibrary;

    public DvdViewController(MyDvdLibrary myDvdLibrary) {
        this.myDvdLibrary = myDvdLibrary;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
    		HttpServletResponse response) {
        Map model = new HashMap();

        List dvdItems = getAllDvds();

        model.put("dvdItems", dvdItems);

        return new ModelAndView("mydvdlibrary", "model", model);
    }

    private List getAllDvds() {
        return myDvdLibrary.getAllDvds();
    }

}