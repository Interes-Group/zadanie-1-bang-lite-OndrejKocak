package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;

import java.util.List;

public abstract class BrownCard extends Card {
    protected Decks decks;

    public BrownCard(String name, Decks decks) {
        super(name);
        this.decks = decks;
    }

    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        this.decks.discardCard(this);
    }
}
