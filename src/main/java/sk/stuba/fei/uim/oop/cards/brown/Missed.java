package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Missed extends BrownCard {
    private static final String CARD_NAME = "Missed";
    public Missed(Decks decks) {
        super(CARD_NAME, decks);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        System.out.println("Your Bang was dodged with missed");
        this.decks.discardCard(this);
    }

    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
        return false;
    }
}
