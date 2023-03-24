package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME="Stagecoach";

    public Stagecoach(Decks decks) {
        super(CARD_NAME, decks);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        List<Card> drawnCards = this.decks.drawCards(2);
        playerOnTurn.takeCards(drawnCards);
        System.out.println("Player " + playerOnTurn.getName() + " drawn 2 cards.");
    }

}
