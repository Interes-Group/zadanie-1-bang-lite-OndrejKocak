package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;

import java.util.List;

public class Indians extends BrownCard {
    private static final String CARD_NAME = "Indians";
    private final int damage;

    public Indians(Decks decks) {
        super(CARD_NAME, decks);
        damage = 1;
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        for (Player target : enemies) {
            boolean hasBang = false;
            for (Card card : target.getCardsInHand()) {
                if (card instanceof Bang) {
                    hasBang = true;
                    target.removeCardFromHand(card);
                    this.decks.discardCard(card);
                    break;
                }
            }
            if (!hasBang) {
                target.removeLives(this.damage);
                System.out.println("Player " + target.getName() + " dont have bang and lost " + this.damage + " live");
                super.checkKill(target, this.decks);
            } else {
                System.out.println("Player " + target.getName() + " discarded bang");
            }
        }
    }

}
