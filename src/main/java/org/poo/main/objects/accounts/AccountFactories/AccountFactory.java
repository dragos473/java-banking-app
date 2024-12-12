package org.poo.main.objects.accounts.AccountFactories;

import org.poo.main.objects.accounts.Account;

public abstract class AccountFactory {
    public Account create(String currency, double interestRate) {
        Account account = createAccount();
        account.register(currency, interestRate);
        return account;
    }
    protected abstract Account createAccount();
}
