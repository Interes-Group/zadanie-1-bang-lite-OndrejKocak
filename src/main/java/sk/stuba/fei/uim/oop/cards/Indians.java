package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Indians extends BrownCard{
    private static final String CARD_NAME="Indians";
    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        for(Player target : enemies){
            boolean hasBang = false;
            for(Card card : target.getCardsInHand()){
                if(card instanceof Bang){
                    hasBang = true;
                    target.removeCardFromHand(card);
                    table.discardCard(card);
                    break;
                }
            }
            if(!hasBang){
                target.removeLive();
                System.out.println("Player "+ target.getName() + " dont have bang and lost 1 live");
                super.checkKill(target, table);
            }
            else {
                System.out.println("Player "+ target.getName() + " discarded bang");
            }
        }
    }

}
