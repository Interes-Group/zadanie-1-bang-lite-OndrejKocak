package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.brown.CatBalou;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

public abstract class Card {
    protected String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void play(Player playerOnTurn, List<Player> enemies){
        System.out.println("Player " + playerOnTurn.getName() + " played card: " + this.name);
    }

    protected final Player selectTarget(List<Player> enemies){
        System.out.println("Available targets: ");
        for(int i = 0; i < enemies.size(); i++){
            Player player = enemies.get(i);
            if(this instanceof CatBalou){
                System.out.println("("+(i+1)+") " + player.getName());
                System.out.println("--> Hand: "+ player.getCardsInHand().size());
                System.out.println("--> Active blue cards: " + player.getCardsInFront().size());
            } else {
                System.out.println("("+(i+1)+") " + player.getName() + "  lives: " + player.getLives());
            }

        }
        int targetIndex = 0;
        while(true){
            targetIndex = KeyboardInput.readInt("Enter the number of your target ");
            if(targetIndex > 0 & targetIndex <= enemies.size()){
                targetIndex--;
                break;
            }
            System.out.println("You entered wrong number. Please try again.");
        }
        return enemies.get(targetIndex);
    }

    protected final void checkKill(Player target, Decks decks){
        if(!target.isAlive()){
            for (Card card : target.die()){
                decks.discardCard(card);
            }
            System.out.println("Player "+target.getName()+" died.");
        }
    }

    public boolean isPlayable(Player playerOnTurn, List<Player> enemies){
        return true;
    }

}
