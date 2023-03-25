package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Prison extends BlueCard {
    private static final String CARD_NAME="Prison";
    private  static final int BOUND=4;
    public Prison() {
        super(CARD_NAME, BOUND);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        System.out.println("Player " + playerOnTurn.getName() + " played card: " + this.name);
        Player target = this.selectTarget(filterEnemiesWithPrison(enemies));
        target.activateCard(this);
        System.out.println("Prison was added in front of " + target.getName());
    }


    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
       return filterEnemiesWithPrison(enemies).size() > 0;
    }
    private List<Player> filterEnemiesWithPrison(List<Player> enemies){
        List<Player> enemiesWithoutPrison = new ArrayList<>();
        for(Player enemy : enemies){
            boolean hasPrison = false;
            for(Card card : enemy.getCardsInFront()){
                if(card instanceof Prison){
                    hasPrison = true;
                    break;
                }
            }
            if(!hasPrison){
                enemiesWithoutPrison.add(enemy);
            }
        }
        return enemiesWithoutPrison;
    }

}
