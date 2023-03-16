package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends Card{
    private static final String CARD_NAME = "Bang";
    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable(Player currentPlayer) {
        return true;
    }
}
