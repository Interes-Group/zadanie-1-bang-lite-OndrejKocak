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
    public void play(Player playerOnTurn, List<Player> playersAlive, Table table){
        System.out.println("Player " + playerOnTurn.getName() + " played card: " + this.name);
        table.discardCard(this);
    }

    protected final Player selectTarget(Player playerOnTurn, List<Player> playersAlive){
        playersAlive.remove(playerOnTurn);
        int targetIndex = 0;
        System.out.println("Available targets: ");
        for(int i = 0; i < playersAlive.size(); i++){
            Player player = playersAlive.get(i);
            System.out.println("("+(i+1)+") " + player.getName() + "  lives: " + player.getLives());
        }
        while(true){
            targetIndex = KeyboardInput.readInt("Enter the number of your target ");
            if(targetIndex > 0 & targetIndex <= playersAlive.size()){
                targetIndex--;
                break;
            }
            System.out.println("You entered wrong number. Please try again.");
        }
        return playersAlive.get(targetIndex);
    }

    public abstract boolean isPlayable(Player currentPlayer);

}
