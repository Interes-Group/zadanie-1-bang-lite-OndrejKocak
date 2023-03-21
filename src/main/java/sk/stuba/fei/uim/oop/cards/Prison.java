package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Prison extends BlueCard{
    private static final String CARD_NAME="Prison";
    public Prison() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        //TODO
    }

    @Override
    public boolean isPlayable(Player currentPlayer, List<Player> enemies) {
        for(Player enemy : enemies){
            boolean hasPrison = false;
            for(Card card : enemy.getActiveBlueCards()){
                if(card instanceof Prison){
                    hasPrison = true;
                    break;
                }
            }
            if(!hasPrison){
                return true;
            }
        }
        return false;
    }
}
