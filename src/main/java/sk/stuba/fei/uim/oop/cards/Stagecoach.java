package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Stagecoach extends BrownCard{
    private static final String CARD_NAME="Stagecoach";

    public Stagecoach() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        List<Card> drawnCards = table.drawCards(2);
        playerOnTurn.takeCards(drawnCards);
        System.out.println("Player " + playerOnTurn.getName() + " drawn 2 cards.");
    }

}
