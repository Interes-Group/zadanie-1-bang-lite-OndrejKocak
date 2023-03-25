package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;

import java.util.List;

public class Bang extends BrownCard {
    private static final String CARD_NAME = "Bang";
    private final int damage;

    public Bang(Decks decks) {
        super(CARD_NAME, decks);
        damage = 1;
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        Player target = super.selectTarget(enemies);
        for (BlueCard card : target.getCardsInFront()) {
            if (card instanceof Barrel) {
                if (card.checkEffect()) {
                    System.out.println("Player " + target.getName() + " avoided " + this.name + " with Barrel");
                    return;
                } else {
                    System.out.println("Barrel wasn't activated.");
                }

            }
        }
        for (Card card : target.getCardsInHand()) {
            if (card instanceof Missed) {
                target.removeCardFromHand(card);
                card.play(playerOnTurn, enemies);
                return;
            }
        }
        target.removeLives(this.damage);
        System.out.println("Player " + target.getName() + " was shot with " + this.name + " and lost " + this.damage + " live.");
        super.checkKill(target, this.decks);
    }

}
