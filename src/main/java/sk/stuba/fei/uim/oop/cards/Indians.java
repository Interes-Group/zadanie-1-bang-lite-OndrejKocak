package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Indians extends Card{
    private static final String CARD_NAME="Indians";
    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        for(Player player : enemies){
            boolean hasBang = false;
            for(Card card : player.getCardsInHand()){
                if(card instanceof Bang){
                    hasBang = true;
                    player.removeCardFromHand(card);
                    table.discardCard(card);
                    break;
                }
            }
            if(!hasBang){
                player.removeLive();
                System.out.println("Player "+ player.getName() + " dont have bang and lost 1 live");
            }
            else {
                System.out.println("Player "+ player.getName() + " discarded bang");
            }
        }
    }

}
