package com.dvdworld.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.dvdworld.model.Dvd;

public class SeedData implements InitializingBean {
    private DvdWorldDao dvdWorldDao;

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dvdWorldDao);
        
        //dvdWorldDao.createOrUpdateDvd(new Dvd(1, "My Dvd 1", "some description text here", 4, 10.0));
        //dvdWorldDao.createOrUpdateDvd(new Dvd(2, "My Dvd 2", "great dvd to buy. You should buy this", 5, 2.0));
        //dvdWorldDao.createOrUpdateDvd(new Dvd(3, "My Dvd 3", "one classic dvd", 1, 4.2));
        //dvdWorldDao.createOrUpdateDvd(new Dvd(4, "My Dvd 4", "some description here as well. pretty new release here.", 11, 2.1));
    }

    public void setDvdWorldDao(DvdWorldDao dvdWorldDao) {
        this.dvdWorldDao = dvdWorldDao;
    }

}