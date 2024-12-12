package org.poo.main.objects.accounts.Cards;

public interface Card {
    String getCardNumber();
    void setBalance(double balance);
    boolean isAvailable();
    void payed();
    void register();
}
