package org.poo.main.objects.Actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.NoSuchDynamicMethodException;
import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.Output;
import org.poo.main.objects.User;
import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.Cards.Card;

public class PayOnline implements Action{
    //Exception err = new Exception("Insufficient funds");
    @Override
    public void execute(CommandInput input) {
        try {
            User user = Bank.getInstance().getUser(input.getEmail());
            boolean found = false;
            for (Account a : user.getAccounts()) {
                Card c= a.getCard(input.getCardNumber()) ;
                if (c != null) {
                double rate = Bank.getInstance().getExchange().getExchangeRate(input.getCurrency(), a.getCurrency());
                double amount = input.getAmount() * rate;
                a.pay(amount);
                c.payed();
                a.cardCleanup();
                found = true;
                break;
                }
            }
            if (!found) {
                Output JSON = Output.getInstance();
                ObjectNode out = JSON.mapper.createObjectNode();
                out.put("command", "payOnline");
                ObjectNode output = JSON.mapper.createObjectNode();
                output.put("description", "Card not found");
                output.put("timestamp", input.getTimestamp());
                out.put("output", output);
                out.put("timestamp", input.getTimestamp());
                JSON.output.add(out);
            }
        } catch (Exception e) {
//            Output JSON = Output.getInstance();
//            ObjectNode out = JSON.mapper.createObjectNode();
//            out.put("command", "payOnline");
//            ObjectNode output = JSON.mapper.createObjectNode();
//            output.put("description", "Insufficient funds");
//            output.put("timestamp", input.getTimestamp());
//            out.put("output", output);
//            out.put("timestamp", input.getTimestamp());
//            JSON.output.add(out);
        }
    }
}
