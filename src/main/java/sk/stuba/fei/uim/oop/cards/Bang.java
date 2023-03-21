package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Bang extends BrownCard{
    private static final String CARD_NAME = "Bang";
    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        Player target = super.selectTarget(playerOnTurn, enemies);
        for(BlueCard card : target.getActiveBlueCards()){
            if(card instanceof Barrel){
                if(card.checkEffect()){
                    System.out.println("Player "+target.getName()+" avoided shot with Barrel");
                    target.removeCardFromActive(card);
                    table.discardCard(card);
                }
                else {
                    System.out.println("Barrel wasn't activated.");
                }

            }
        }
        for(Card card : target.getCardsInHand()){
            if(card instanceof Missed){
                System.out.println("Player "+ target.getName() + " dodged your Bang with Missed.");
                target.removeCardFromHand(card);
                table.discardCard(card);
                return;
            }
        }
        target.removeLive();
        System.out.println("Player "+ target.getName() + " was shot with Bang and lost 1 live.");
        super.checkKill(target, table);
    }

}
