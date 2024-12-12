package org.poo.main.objects.Actions;

import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.User;
import org.poo.main.objects.accounts.Account;


public class DeleteCard implements  Action{
    @Override
    public void execute(CommandInput input) {
        //TODO
        try {
            User user = Bank.getInstance().getUser(input.getEmail());
            for (Account a : user.getAccounts()) {
                a.removeCard(input.getCardNumber());
            }
        } catch (Exception e) {
            System.out.println("Card not found");
        }

    }
}
