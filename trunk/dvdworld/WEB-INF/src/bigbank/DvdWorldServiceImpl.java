package bigbank;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.Assert;

public class DvdWorldServiceImpl implements DvdWorldService {
    private DvdWorldDao dvdWorldDao;

    // Not used unless you declare a <protect-pointcut>
    @Pointcut("execution(* bigbank.DvdWorldServiceImpl.*(..))")
    public void myPointcut() {}

    public DvdWorldServiceImpl(DvdWorldDao dvdWorldDao) {
        Assert.notNull(dvdWorldDao);
        this.dvdWorldDao = dvdWorldDao;
    }

    public Account[] findAccounts() {
        return this.dvdWorldDao.findAccounts();
    }

    public Account post(Account account, double amount) {
        Assert.notNull(account);

        // We read a DvdWorld account from DAO so it reflects the latest balance
        Account a = dvdWorldDao.readAccount(account.getId());
        if (account == null) {
            throw new IllegalArgumentException("Couldn't find requested account");
        }

        a.setBalance(a.getBalance() + amount);
        dvdWorldDao.createOrUpdateAccount(a);
        return a;
    }

    public Account readAccount(Long id) {
        return dvdWorldDao.readAccount(id);
    }
}