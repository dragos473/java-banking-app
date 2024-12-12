package org.poo.main.objects.accounts.AccountFactories;

import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.SavingsAccount;

public class SavingsAccountFactory extends AccountFactory {
    @Override
    protected Account createAccount() {
        return new SavingsAccount();
    }
}
