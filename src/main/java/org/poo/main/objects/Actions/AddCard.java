package org.poo.main.objects.Actions;

import org.poo.fileio.CommandInput;
import org.poo.main.objects.Bank;
import org.poo.main.objects.User;
import org.poo.main.objects.accounts.Cards.Card;
import org.poo.main.objects.accounts.Cards.CardFactory.CardFactory;
import org.poo.main.objects.accounts.Cards.CardFactory.DefaultCardFactory;

public class AddCard implements Action {
    @Override
    public void execute(CommandInput input) {
        //TODO: add a card to the user
        CardFactory factory;
        Card card;
            factory = new DefaultCardFactory();
        try {
            User user = Bank.getInstance().getUser(input.getEmail());
            card = factory.create(user.getAccount(input.getAccount()).getBalance());
            user.getAccount(input.getAccount()).addCard(card);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
