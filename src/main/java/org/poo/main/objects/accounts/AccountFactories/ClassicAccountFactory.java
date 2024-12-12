package org.poo.main.objects.accounts.AccountFactories;

import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.ClassicAccount;

public class ClassicAccountFactory extends AccountFactory {
    @Override
    protected Account createAccount() {
        return new ClassicAccount();
    }
}
