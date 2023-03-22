package sk.stuba.fei.uim.oop.cards.blue;


import sk.stuba.fei.uim.oop.cards.Card;

import java.util.Random;

public abstract class BlueCard extends Card {
    protected Random random;
    protected int bound;
    public BlueCard(String name, int bound) {
        super(name);
        random = new Random();
        this.bound = bound;
    }

    public boolean checkEffect(){
        if(random.nextInt(this.bound) == 1){
            return true;
        }
        return false;
    }

}
