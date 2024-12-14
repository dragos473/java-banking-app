package org.poo.main.objects.accounts;
import lombok.Getter;
import lombok.Setter;
import org.poo.main.Transactions;
import org.poo.main.objects.accounts.Cards.Card;
import org.poo.utils.Utils;

import java.util.ArrayList;

@Setter
@Getter
public class ClassicAccount implements Account {
    private String IBAN;
    private String currency;
    private String alias;
    private double balance;
    private double minBalance;
    private double interestRate;
    private ArrayList<Card> cards;

    public void addCard(Card card) {
//        for (int i = 0; i < cards.size(); i++) {
//            if (!cards.get(i).isAvailable()) {
//                cards.get(i).setCardNumber(card.getCardNumber());
//                cards.get(i).unfreeze();
//                cards.get(i).setBalance(balance);
//                return;
//            }
//        }
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
    public void pay(double amount) throws Exception {

        if (balance - amount > 0) {
            balance -= amount;
        } else {
            if (balance < amount) {
                throw new Exception("Insufficient funds");
            }
            throw new Exception("Funds below minimum balance");
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    public void register(String currency, double interestRate) {
        IBAN = Utils.generateIBAN();
        balance = 0.0;
        minBalance = 0.0;
        this.currency = currency;
        this.interestRate = interestRate;
        cards = new ArrayList<>();
    }
}
