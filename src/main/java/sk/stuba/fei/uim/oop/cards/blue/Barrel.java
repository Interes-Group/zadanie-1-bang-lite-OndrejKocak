package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Barrel extends BlueCard {
    private static final String CARD_NAME="Barrel";
    private static final int BOUND=4;
    public Barrel() {
        super(CARD_NAME, BOUND);
    }


    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
       for(Card card: playerOnTurn.getCardsInFront()){
           if(card instanceof Barrel){
               System.out.println(this.name + " is not playable. You already have one in front of you.");
               return false;
           }
       }
       return true;
    }
}
