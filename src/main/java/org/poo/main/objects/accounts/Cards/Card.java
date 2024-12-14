package org.poo.main.objects.accounts.Cards;

public interface Card {
    //Getters
    String getCardNumber();
    boolean isAvailable();
    //Setters
    void setBalance(double balance);
    void setCardNumber(String cardNumber);
    //Card methods
    void freeze();
    void unfreeze();
    void payed();
    void register();
}
