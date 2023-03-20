package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Missed extends Card{
    private static final String CARD_NAME = "Missed";
    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable(Player currentPlayer, List<Player> enemies) {
        return false;
    }
}
