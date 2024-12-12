package org.poo.main.objects.Actions;

import org.poo.fileio.CommandInput;

import java.util.HashMap;
import java.util.Map;

public class ActionMap {
    Map<String, Action> map = new HashMap<>();

    public ActionMap() {
        map.put("addAccount", new AddAccount());
        map.put("printUsers", new PrintUsers());
        map.put("addFunds", new AddFunds());
        map.put("createCard", new AddCard());
        map.put("createOneTimeCard", new AddOneTimeCard());
        map.put("deleteAccount", new DeleteAccount());
        map.put("deleteCard", new DeleteCard());
        map.put("payOnline", new PayOnline());
    }
    public void execute(CommandInput input) throws NoSuchMethodException {
        if (!map.containsKey(input.getCommand())) {
            throw new NoSuchMethodException();
        }
        map.get(input.getCommand()).execute(input);
    }
}
