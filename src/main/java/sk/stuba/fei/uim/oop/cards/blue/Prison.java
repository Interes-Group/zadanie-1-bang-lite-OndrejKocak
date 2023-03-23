package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Prison extends BlueCard {
    private static final String CARD_NAME="Prison";
    public Prison() {
        super(CARD_NAME, 4);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        filterEnemiesWithPrison(enemies);
        Player target = super.selectTarget(enemies);
        target.activateCard(this);
    }

    private void filterEnemiesWithPrison(List<Player> enemies){
        for(Player enemy : enemies){
            for(BlueCard card: enemy.getCardsInFront()){
                if(card instanceof Prison){
                    System.out.println("You cannot use prison on player "+enemy.getName() + " because he/she has prison already active.");
                    enemies.remove(enemy);
                }
            }
        }
    }

    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
        for(Player enemy : enemies){
            boolean hasPrison = false;
            for(Card card : enemy.getCardsInFront()){
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
