package org.poo.main.objects;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.CommandInput;
import org.poo.fileio.ExchangeInput;
import org.poo.fileio.ObjectInput;
import org.poo.fileio.UserInput;
import org.poo.main.objects.Actions.Action;
import org.poo.main.objects.Actions.PrintUsers;

import java.util.ArrayList;

@Setter
@Getter
public class Bank {
    private static Bank instance;
    private ArrayList<User> Users;
    private Exchange exchange;
    private Bank(){
        //TODO: init values
        Users = new ArrayList<>();
        UserInput[] get = Input.getInstance(new ObjectInput()).inputData.getUsers();
        for (UserInput ui : get) {
            Users.add(new User(ui.getFirstName(), ui.getLastName(), ui.getEmail()));
        }
        ExchangeInput[] commands = Input.getInstance(new ObjectInput()).inputData.getExchangeRates();
        exchange = new Exchange();
        for (ExchangeInput ei : commands) {
            getExchange().addRate(ei.getFrom(), ei.getTo(), ei.getRate());
        }
    }

    /**
     * singleton initializer, that only allows one instance of Bank
     * @return
     */
    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }
    public static void deleteInstance() {
        instance = null;
    }

    public User getUser(String email) throws NoSuchMethodException {
        for (User u : Users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        throw new NoSuchMethodException();
    }
}