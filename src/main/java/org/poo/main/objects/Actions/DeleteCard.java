package org.poo.main.objects.Actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.Output;
import org.poo.main.objects.User;
import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.Cards.Card;


public class DeleteCard implements  Action{
    @Override
    public void execute(CommandInput input) {
//        System.out.println("First step of deletion");

        try {
//            System.out.println("Second step of deletion");
            User user = Bank.getInstance().getUser(input.getEmail());
//            System.out.println("Third step of deletion");
            System.out.println(user.getAccounts());

            for (Account a : user.getAccounts()) {
                for (Card c : a.getCards()) {
                    System.out.println(c.getCardNumber());
                }
                if (a.getCard(input.getCardNumber()) == null) {
                    continue;
                }
//                System.out.println("Fourth step of deletion");
                a.removeCard(input.getCardNumber());
//                System.out.println("Fifth step of deletion");

                ObjectNode output = Output.getInstance().mapper.createObjectNode();
                output.put("account", a.getIBAN());
                output.put("card", input.getCardNumber());
                output.put("cardHolder", user.getEmail());
                output.put("description", "The card has been destroyed");
                output.put("timestamp", input.getTimestamp());
                user.getTransactions().addTransaction(output, a.getIBAN());
                System.out.println(input.getTimestamp() + ".Card deleted " + input.getCardNumber()+ " (account: " + a.getIBAN() + ")");
                return;
            }
        } catch (Exception e) {
            System.out.println("Card not found");
        }

    }
}
