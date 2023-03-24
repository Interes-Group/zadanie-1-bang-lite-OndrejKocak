package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Dynamite extends BlueCard{
    private static final String CARD_NAME = "Dynamite";
    private static final int BOUND=8;
    public Dynamite() {
        super(CARD_NAME, BOUND);
    }



    public void explode(Player player){
        System.out.println("Dynamite exploded in "+player.getName()+"'s hands.");
        player.removeLive(3);
        if (!player.isAlive()) {
            player.die();
        }
    }

    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
       for(Card card : playerOnTurn.getCardsInFront()){
           if(card instanceof Dynamite){
               return false;
           }
       }
       return true;
    }
}
