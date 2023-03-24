package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;

import java.util.List;

public abstract class BrownCard extends Card {
    public BrownCard(String name) {
        super(name);
    }

    public void play(Player playerOnTurn, List<Player> enemies, Decks decks){
        super.play(playerOnTurn, enemies, decks);
        decks.discardCard(this);
    }
}
