package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME="Stagecoach";

    public Stagecoach() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Decks decks) {
        super.play(playerOnTurn, enemies, decks);
        List<Card> drawnCards = decks.drawCards(2);
        playerOnTurn.takeCards(drawnCards);
        System.out.println("Player " + playerOnTurn.getName() + " drawn 2 cards.");
    }

}
