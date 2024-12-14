package org.poo.main.objects.accounts;

import org.poo.main.Transactions;
import org.poo.main.objects.accounts.Cards.Card;

import java.util.List;


public interface Account {
    //Setters
    void setAlias(String alias);
    void setBalance(double balance);
    void setMinBalance(double minBalance);
    //Getters
    String getIBAN();
    String getCurrency();
    String getAlias();
    double getBalance();
    double getMinBalance();
    double getInterestRate();
    List<Card> getCards();
    //Card methods
    void addCard(Card card);
    Card getCard(String cardNumber);
    void removeCard(String cardNumber);
    //Account methods
    void pay(double amount) throws Exception;
    void deposit(double amount);
    void register(String currency, double interestRate);
}
