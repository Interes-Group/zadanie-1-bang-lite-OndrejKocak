package sk.stuba.fei.uim.oop.cards.blue;


import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public abstract class BlueCard extends Card {
    protected Random random;
    private final int bound;

    public BlueCard(String name, int bound) {
        super(name);
        random = new Random();
        this.bound = bound;
    }


    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        playerOnTurn.activateCard(this);
        System.out.println(this.name + " was added in front you.");
    }

    public boolean checkEffect() {
        return random.nextInt(this.bound) == 1;
    }
}
