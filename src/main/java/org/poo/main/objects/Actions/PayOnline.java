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
    User user;
    Card usedCard;
    CommandInput cardInfo = new CommandInput();
    boolean OneTimeUsed = false;
    @Override
    public void execute(CommandInput input) {
        try {
            user = Bank.getInstance().getUser(input.getEmail());
            boolean found = false;
            for (Account a : user.getAccounts()) {
                Card c = a.getCard(input.getCardNumber());
                if (c == null) {
                    continue;
                }
                if (!c.isAvailable()) {
                    ObjectNode output = Output.getInstance().mapper.createObjectNode()
                            .put("description", "The card is frozen")
                            .put("timestamp", input.getTimestamp());
                    user.getTransactions().addTransaction(output, a.getIBAN());
                    return;
                }
                double rate = Bank.getInstance().getExchange().getExchangeRate(input.getCurrency(), a.getCurrency());
                double amount = input.getAmount() * rate;
                a.pay(amount);
                //usedCard = c;
                found = true;
                try {
                    c.payed();
                } catch (UnsupportedOperationException e) {
                    if (e.getMessage().equals("OneTimeUseCard")) {
                        cardInfo.setEmail(input.getEmail());
                        cardInfo.setCardNumber(input.getCardNumber());
                        cardInfo.setTimestamp(input.getTimestamp());
                        cardInfo.setAccount(a.getIBAN());
                        OneTimeUsed = true;
                    }
                }

                    ObjectNode output = Output.getInstance().mapper.createObjectNode()
                            .put("amount", amount)
                            .put("commerciant", input.getCommerciant())
                            .put("description", "Card payment")
//                        .put("account", a.getIBAN())
                            .put("timestamp", input.getTimestamp());
                    user.getTransactions().addTransaction(output, a.getIBAN());
                    if (OneTimeUsed) {
                        new DeleteCard().execute(cardInfo);
                        new AddOneTimeCard().execute(cardInfo);
                    }
                    break;
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
            } catch(Exception e){
//                if (OneTimeUsed) {
//                    new DeleteCard().execute(cardInfo);
//                    new AddOneTimeCard().execute(cardInfo);
//                }
                if (e.getMessage().equals("Funds below minimum balance")) {
                    //usedCard.freeze();
                    ObjectNode output = Output.getInstance().mapper.createObjectNode()
                            .put("description", "You have reached the minimum amount of funds, the card will be frozen")
                            .put("timestamp", input.getTimestamp());
                    user.getTransactions().addTransaction(output, input.getAccount());
                } else {
                    ObjectNode output = Output.getInstance().mapper.createObjectNode()
                            .put("description", "Insufficient funds")
                            .put("timestamp", input.getTimestamp());
                    user.getTransactions().addTransaction(output, input.getAccount());
                }
            }
    }
}
