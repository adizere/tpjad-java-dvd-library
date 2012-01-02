package bigbank;

public interface DvdWorldDao {
    public Account readAccount(Long id);
    public void createOrUpdateAccount(Account account);
    public Account[] findAccounts();
}