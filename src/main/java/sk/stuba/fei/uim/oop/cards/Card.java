package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
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
    public void play(Player playerOnTurn, List<Player> enemies, Table table){
        System.out.println("Player " + playerOnTurn.getName() + " played card: " + this.name);
        table.discardCard(this);
    }

    protected final Player selectTarget(Player playerOnTurn, List<Player> enemies){
        int targetIndex = 0;
        System.out.println("Available targets: ");
        for(int i = 0; i < enemies.size(); i++){
            Player player = enemies.get(i);
            if(this instanceof Bang){
                System.out.println("("+(i+1)+") " + player.getName() + "  lives: " + player.getLives());
            } else if (this instanceof CatBalou) {
                System.out.println("("+(i+1)+") " + player.getName());
                System.out.println("--> Hand: "+ player.getCardsInHand().size());
                System.out.println("--> Active blue cards: " + player.getActiveBlueCards().size());
            }

        }
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

    public boolean isPlayable(Player currentPlayer, List<Player> enemies){
        return true;
    }

}
