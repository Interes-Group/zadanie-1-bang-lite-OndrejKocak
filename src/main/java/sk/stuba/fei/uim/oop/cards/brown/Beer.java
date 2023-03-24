package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Beer extends BrownCard {
    private static final String CARD_NAME = "Beer";
    public Beer(){
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Decks decks) {
        super.play(playerOnTurn, enemies, decks);
        playerOnTurn.addLive();
        System.out.println(playerOnTurn.getName() + "s lives increased to " + playerOnTurn.getLives());
    }

}
