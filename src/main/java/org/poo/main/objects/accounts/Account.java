package org.poo.main.objects.accounts;

import org.poo.main.Transactions;
import org.poo.main.objects.accounts.Cards.Card;

import java.util.ArrayList;


public interface Account {
    String getIBAN();
    double getBalance();
    String getCurrency();
    String getAlias();
    void setAlias(String alias);
    double getInterestRate();
    ArrayList<Card> getCards();
    void addCard(Card card);
    Card getCard(String cardNumber);
    void removeCard(String cardNumber);
    void setBalance(double balance);
    void pay(double amount) throws Exception;
    void deposit(double amount);
    void cardCleanup();
    void register(String currency, double interestRate);
}
