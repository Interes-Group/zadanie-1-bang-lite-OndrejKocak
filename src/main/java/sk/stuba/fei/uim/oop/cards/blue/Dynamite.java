package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Dynamite extends BlueCard{
    private static final String CARD_NAME = "Dynamite";
    private static final int BOUND=8;
    private final int damage;
    public Dynamite() {
        super(CARD_NAME, BOUND);
        this.damage = 3;
    }



    public void explode(Player player, Decks decks){
        System.out.println("Dynamite exploded in "+player.getName()+"'s hands. You lost "+ this.damage+" lives.");
        player.removeLives(this.damage);
        checkKill(player, decks);
    }

    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies, boolean excuse) {
       for(Card card : playerOnTurn.getCardsInFront()){
           if(card instanceof Dynamite){
               if(excuse){
                   System.out.println(this.name+ " is not playable because you already have one in front of you.");
               }
               return false;
           }
       }
       return true;
    }
}
