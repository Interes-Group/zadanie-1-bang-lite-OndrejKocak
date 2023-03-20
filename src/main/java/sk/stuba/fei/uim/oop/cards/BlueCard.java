package sk.stuba.fei.uim.oop.cards;

public abstract class BlueCard extends Card{
    public BlueCard(String name) {
        super(name);
    }

    public abstract boolean checkEffect();
}
