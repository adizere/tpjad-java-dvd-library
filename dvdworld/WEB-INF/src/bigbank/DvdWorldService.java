package bigbank;

import org.springframework.security.access.prepost.PreAuthorize;


public interface DvdWorldService {

    public Account readAccount(Long id);

    public Account[] findAccounts();

    @PreAuthorize(
            "hasRole('ROLE_SUPERVISOR') or " +
            "hasRole('ROLE_TELLER') and (#account.balance + #amount >= -#account.overdraft)" )
    public Account post(Account account, double amount);
}