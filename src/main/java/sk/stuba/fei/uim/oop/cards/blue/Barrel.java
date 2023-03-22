package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Barrel extends BlueCard {
    private static final String CARD_NAME="Barrel";
    public Barrel() {
        super(CARD_NAME, 4);
    }


    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        playerOnTurn.activateCard(this);
        System.out.println("Barrel was added to your active cards.");
    }

    @Override
    public boolean isPlayable(Player currentPlayer, List<Player> enemies) {
       for(Card card: currentPlayer.getActiveBlueCards()){
           if(card instanceof Barrel){
               return false;
           }
       }
       return true;
    }
}
