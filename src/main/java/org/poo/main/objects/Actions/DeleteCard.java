package org.poo.main.objects.Actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.Output;
import org.poo.main.objects.User;
import org.poo.main.objects.accounts.Account;


public class DeleteCard implements  Action{
    @Override
    public void execute(CommandInput input) {
        //TODO
        try {
            User user = Bank.getInstance().getUser(input.getEmail());
            for (Account a : user.getAccounts()) {
                if (a.getCard(input.getCardNumber()) == null) {
                    continue;
                }
                a.removeCard(input.getCardNumber());
                ObjectNode output = Output.getInstance().mapper.createObjectNode();
                output.put("account", a.getIBAN());
                output.put("card", input.getCardNumber());
                output.put("cardHolder", user.getEmail());
                output.put("description", "The card has been destroyed");
                output.put("timestamp", input.getTimestamp());
                user.getTransactions().addTransaction(output, a.getIBAN());
                return;
            }
        } catch (Exception e) {
            System.out.println("Card not found");
        }

    }
}
