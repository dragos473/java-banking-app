package org.poo.main;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.objects.Commerciants;
import org.poo.main.objects.Output;
import org.poo.main.objects.accounts.Account;

import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private Output JSON = Output.getInstance();
    private ArrayNode transactions = JSON.mapper.createArrayNode();
    private List<String> accounts = new ArrayList<>();

    public void addTransaction(ObjectNode transaction, String IBAN) {
        transactions.add(transaction);
        accounts.add(IBAN);
    }

    public void printTransactions(int timestamp) {
        ArrayNode transactionsOut = JSON.mapper.createArrayNode();
        transactionsOut.addAll(transactions);

        ObjectNode out = JSON.mapper.createObjectNode();
        out.put("command", "printTransactions");
        out.put("output", transactionsOut);
        out.put("timestamp", timestamp);
        JSON.output.add(out);
    }

    public void report(int timestampStart, int timestampEnd, Account account, int timestamp) {
        ObjectNode output = JSON.mapper.createObjectNode();
        ArrayNode transactionsOut = JSON.mapper.createArrayNode();
        int lastTimestamp = -1;
        for (int i = 0; i < transactions.size(); i++) {
            ObjectNode transaction = (ObjectNode) transactions.get(i);
            int transactionTimestamp = transaction.get("timestamp").asInt();
            if (transactionTimestamp >= timestampStart && transactionTimestamp <= timestampEnd) {
                if (!accounts.get(i).equals(account.getIBAN())) {
                    continue;
                }
                if (transactionTimestamp != lastTimestamp) {
                    transactionsOut.add(transaction);
                    lastTimestamp = transactionTimestamp;
                }
            }
        }
        output.put("balance", account.getBalance())
                .put("currency", account.getCurrency())
                .put("IBAN", account.getIBAN());
        output.put("transactions", transactionsOut);

        ObjectNode out = JSON.mapper.createObjectNode();
        out.put("command", "report");
        out.put("output", output);
        out.put("timestamp", timestamp);
        JSON.output.add(out);
    }

    public void spendingReport(int timestampStart, int timestampEnd, Account account, int timestamp) {
        Commerciants commerciants = new Commerciants();
        ArrayNode transactionsOut = JSON.mapper.createArrayNode();

        for (int i = 0; i < transactions.size(); i++) {
            ObjectNode transaction = (ObjectNode) transactions.get(i);

            if (!transaction.has("commerciant")) {
                continue;
            }

            if (!accounts.get(i).equals(account.getIBAN())) {
                continue;
            }

            if(transaction.get("timestamp").asInt() < timestampStart || transaction.get("timestamp").asInt() > timestampEnd) {
                continue;
            }

            String commerciant = transaction.get("commerciant").asText();
            double amount = transaction.get("amount").asDouble();
            commerciants.addCommerciants(commerciant, amount);
            transactionsOut.add(transaction);
        }

        ObjectNode output = JSON.mapper.createObjectNode();
        ArrayNode arr = JSON.mapper.createArrayNode();
        for (int i = 0; i < commerciants.getMoneySpent().size(); i++) {
            ObjectNode commerciant = JSON.mapper.createObjectNode();
            commerciant.put("commerciant", commerciants.getNames().get(i))
                    .put("total", commerciants.getMoneySpent().get(i));
            arr.add(commerciant);
        }

        output.put("balance", account.getBalance());
        output.put("commerciants", arr);
        output.put("currency", account.getCurrency())
                .put("IBAN", account.getIBAN());
        output.put("transactions", transactionsOut);

        ObjectNode out = JSON.mapper.createObjectNode();
        out.put("command", "spendingsReport");
        out.put("output", output);
        out.put("timestamp", timestamp);

        JSON.output.add(out);
    }
}
