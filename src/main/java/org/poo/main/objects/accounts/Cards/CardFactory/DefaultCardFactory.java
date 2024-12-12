package org.poo.main.objects.accounts.Cards.CardFactory;

import org.poo.main.objects.accounts.Cards.Card;
import org.poo.main.objects.accounts.Cards.DefaultCard;

public class DefaultCardFactory extends CardFactory {
    @Override
    protected Card createCard() {
        return new DefaultCard();
    }
}
