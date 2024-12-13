package org.poo.main;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.main.objects.Output;

public class Transactions {
    private Output JSON = Output.getInstance();
    private ArrayNode transactions = JSON.mapper.createArrayNode();

    public void addTransaction(ObjectNode transaction) {
        transactions.add(transaction);
    }

    public void printTransactions(int timestamp) {
        //For some reason, future transactions are printed as well.
        ArrayNode transactionsOut = JSON.mapper.createArrayNode();
        for (int i = 0; i < this.transactions.size(); i++) {
            if(this.transactions.get(i).get("timestamp").intValue() < timestamp) {
                transactionsOut.add(this.transactions.get(i));
            }
        }
        ObjectNode out = JSON.mapper.createObjectNode();
        out.put("command", "printTransactions");
        out.put("output", transactionsOut);
        out.put("timestamp", timestamp);
        JSON.output.add(out);
    }
}
