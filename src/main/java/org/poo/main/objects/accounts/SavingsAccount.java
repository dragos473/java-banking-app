package org.poo.main.objects.accounts;

import lombok.Getter;
import lombok.Setter;
import org.poo.main.Transactions;
import org.poo.main.objects.accounts.Cards.Card;
import org.poo.utils.Utils;

import java.util.ArrayList;
@Setter
@Getter
public class SavingsAccount implements Account {
    private String IBAN;
    private double balance;
    private String currency;
    private ArrayList<Card> cards;
    private String alias;
    private double interestRate;


    public void addCard(Card card) {
        //card.setBalance(balance);
        cards.add(card);
        cards.getLast().setBalance(balance);
    }
    public Card getCard(String cardNumber) {
        for (Card c : cards) {
            if (c.getCardNumber().equals(cardNumber)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void removeCard(String cardNumber) {
        cards.removeIf(c -> c.getCardNumber().equals(cardNumber));
    }

    @Override
    public void pay(double amount) throws Exception {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new Exception("Insufficient funds");
        }
    }
    public void deposit(double amount) {
        balance += amount;
    }

    public void cardCleanup() {
        cards.removeIf(c -> !c.isAvailable());
    }

    public void register(String currency, double interestRate) {
        IBAN = Utils.generateIBAN();
        balance = 0.0;
        this.currency = currency;
        this.interestRate = interestRate;
        cards = new ArrayList<>();
    }
}
