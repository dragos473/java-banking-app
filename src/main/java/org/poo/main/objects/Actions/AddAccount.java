package org.poo.main.objects.Actions;

import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.AccountFactories.AccountFactory;
import org.poo.main.objects.accounts.AccountFactories.ClassicAccountFactory;
import org.poo.main.objects.accounts.AccountFactories.SavingsAccountFactory;

public class AddAccount  implements Action {
    @Override
    public void execute(CommandInput input) {
        AccountFactory factory;
        Account account;
        if (input.getAccountType().equals("SavingsAccount")) {
            factory = new SavingsAccountFactory();
            account = factory.create(input.getCurrency(), input.getInterestRate());
        } else {
            factory = new ClassicAccountFactory();
            account = factory.create(input.getCurrency(), 0);
        }
        try {
            Bank.getInstance().getUser(input.getEmail()).getAccounts().add(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
