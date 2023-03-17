package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public abstract class Card {
    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void play(Player playerOnTurn, List<Player> playersAlive){
        System.out.println("Player " + playerOnTurn.getName() + " played card: " + this.name);
    }

    public abstract boolean isPlayable(Player currentPlayer);

}
