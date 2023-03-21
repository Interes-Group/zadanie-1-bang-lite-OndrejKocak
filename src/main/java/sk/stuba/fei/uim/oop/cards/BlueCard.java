package sk.stuba.fei.uim.oop.cards;


import java.util.Random;

public abstract class BlueCard extends Card{
    protected Random random;
    public BlueCard(String name) {
        super(name);
        random = new Random();
    }

    public boolean checkEffect(){
        if(random.nextInt(4) == 1){
            return true;
        }
        return false;
    }

}
