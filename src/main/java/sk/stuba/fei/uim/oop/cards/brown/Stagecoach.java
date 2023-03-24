package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME="Stagecoach";
    private final int numberOfCardsToDraw;

    public Stagecoach(Decks decks) {
        super(CARD_NAME, decks);
        this.numberOfCardsToDraw = 2;
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        List<Card> drawnCards = this.decks.drawCards(this.numberOfCardsToDraw);
        playerOnTurn.takeCards(drawnCards);
        System.out.println("Player " + playerOnTurn.getName() + " drawn "+this.numberOfCardsToDraw+" cards.");
    }

    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
        return decks.getNumberOfAvailableCards() >= this.numberOfCardsToDraw;
    }
}
