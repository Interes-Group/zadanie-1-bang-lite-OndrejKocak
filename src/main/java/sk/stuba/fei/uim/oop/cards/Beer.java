package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Beer extends Card{
    private static final String CARD_NAME = "Beer";
    public Beer(){
        super(CARD_NAME);
    }

    @Override
    public void play(Player player, List<Player> playersAlive) {
        super.play(player, playersAlive);
        player.addLive();
    }

    @Override
    public boolean isPlayable(Player currentPlayer) {
        return true;
    }
}
