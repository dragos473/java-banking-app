package org.poo.main.objects.accounts.Cards;

import lombok.Getter;
import lombok.Setter;
import org.poo.utils.Utils;

@Setter
@Getter
public class OneTimeUseCard implements Card {
    private String cardNumber;
    private double balance;
    private boolean available;

    public void register() {
        cardNumber = Utils.generateCardNumber();
        available = true;
    }

    @Override
    public void freeze() {
        available = false;
    }

    public void unfreeze() {
        available = true;
    }

    public void payed() {
        throw new UnsupportedOperationException("OneTimeUseCard");
    }
}
