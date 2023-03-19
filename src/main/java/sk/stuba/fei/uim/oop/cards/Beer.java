package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Beer extends Card{
    private static final String CARD_NAME = "Beer";
    public Beer(){
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> playersAlive, Table table) {
        super.play(playerOnTurn, playersAlive, table);
        playerOnTurn.addLive();
        System.out.println(playerOnTurn.getName() + "s lives increased to " + playerOnTurn.getLives());
    }

    @Override
    public boolean isPlayable(Player currentPlayer) {
        return true;
    }
}
