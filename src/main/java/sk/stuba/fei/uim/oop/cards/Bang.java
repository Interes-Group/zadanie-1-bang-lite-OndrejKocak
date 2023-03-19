package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;

import java.util.List;

public class Bang extends Card{
    private static final String CARD_NAME = "Bang";
    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public void play(Player playerOnTurn, List<Player> playersAlive, Table table) {
        super.play(playerOnTurn, playersAlive, table);
        Player target = super.selectTarget(playerOnTurn, playersAlive);
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
    }

    @Override
    public boolean isPlayable(Player currentPlayer) {
        return true;
    }
}
