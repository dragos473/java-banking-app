package org.poo.main.objects;

import lombok.Getter;
import lombok.Setter;
import org.poo.main.objects.Actions.Action;
import org.poo.main.objects.Actions.PrintUsers;
import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.Cards.Card;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private List<Account> Accounts;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Accounts = new ArrayList<>();
    }
    public Account getAccount(String IBAN) throws NoSuchMethodException {
        for (Account a : Accounts) {
            if (a.getIBAN().equals(IBAN)) {
                return a;
            }
        }
        throw new NoSuchMethodException();
    }
    public void removeAccount(String IBAN) {
        Accounts.removeIf(a -> a.getIBAN().equals(IBAN));
    }
}
