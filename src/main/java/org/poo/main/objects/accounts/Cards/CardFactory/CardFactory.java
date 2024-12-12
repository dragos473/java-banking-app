package org.poo.main.objects.accounts.Cards.CardFactory;

import org.poo.main.objects.accounts.Account;
import org.poo.main.objects.accounts.Cards.Card;

public abstract class CardFactory {
    public Card create(double balance) {
        Card card = createCard();
        card.register();
        return card;
    }
    protected abstract Card createCard();
}
