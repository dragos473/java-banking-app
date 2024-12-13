package org.poo.main.objects.Actions;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.Output;

public class PrintTransactions implements Action {
    @Override
    public void execute(CommandInput input) {
        try {
            Bank.getInstance().getUser(input.getEmail())
                    .getTransactions().printTransactions(input.getTimestamp());
        } catch (Exception e) {
            e.printStackTrace();
    }
}
}
