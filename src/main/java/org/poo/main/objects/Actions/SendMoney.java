package org.poo.main.objects.Actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.Output;
import org.poo.main.objects.User;
import org.poo.main.objects.accounts.Account;

public class SendMoney implements Action{
    @Override
    public void execute(CommandInput input) {
        if (!input.getAccount().contains("RO")) {
            return;
        }
        try {
            Account payer = Bank.getInstance().getUser(input.getEmail()).getAccount(input.getAccount());
            for (User u : Bank.getInstance().getUsers()) {
                if (u.getAccount(input.getReceiver()) != null) {
                    Account payee  = u.getAccount(input.getReceiver());
                    payer.pay(input.getAmount());
                    double rate = Bank.getInstance().getExchange().getExchangeRate(payer.getCurrency(), payee.getCurrency());
                    double amount = input.getAmount() * rate;
                    payee.deposit(amount);
                    ObjectNode payerOut = Output.getInstance().mapper.createObjectNode()
                            .put("amount", input.getAmount() + " " + payer.getCurrency())
                            .put("description", input.getDescription())
                            .put("receiverIBAN", payee.getIBAN())
                            .put("senderIBAN", payer.getIBAN())
                            .put("timestamp", input.getTimestamp())
                            .put("transferType", "sent");
                    Bank.getInstance().getUser(input.getEmail())
                            .getTransactions().addTransaction(payerOut);
                    ObjectNode payeeOut = Output.getInstance().mapper.createObjectNode()
                            .put("amount", input.getAmount() + " " + payer.getCurrency())
                            .put("description", input.getDescription())
                            .put("receiverIBAN", payee.getIBAN())
                            .put("senderIBAN", payer.getIBAN())
                            .put("timestamp", input.getTimestamp())
                            .put("transferType", "received");
                    u.getTransactions().addTransaction(payeeOut);
                    break;
                }
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
