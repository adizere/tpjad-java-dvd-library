package bigbank;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class SeedData implements InitializingBean{
    private DvdWorldDao dvdWorldDao;

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dvdWorldDao);
        dvdWorldDao.createOrUpdateAccount(new Account("rod"));
        dvdWorldDao.createOrUpdateAccount(new Account("dianne"));
        dvdWorldDao.createOrUpdateAccount(new Account("scott"));
        dvdWorldDao.createOrUpdateAccount(new Account("peter"));
    }

    public void setDvdWorldDao(DvdWorldDao dvdWorldDao) {
        this.dvdWorldDao = dvdWorldDao;
    }

}