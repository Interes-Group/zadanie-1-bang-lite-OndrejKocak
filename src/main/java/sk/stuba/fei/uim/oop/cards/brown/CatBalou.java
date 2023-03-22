package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;
import java.util.Random;

public class CatBalou extends BrownCard {
    private static final String CARD_NAME="Cat Balou";
    private final Random random;
    public CatBalou() {
        super(CARD_NAME);
        random = new Random();
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies, Table table) {
        super.play(playerOnTurn, enemies, table);
        this.filterEnemiesWithoutCards(enemies);
        Player target = super.selectTarget(enemies);
        int cardInHand = target.getNumberOfCardsHand();
        int activeBlueCards = target.getNumberOfCardsActiveBlueCards();
        if(cardInHand > 0 & activeBlueCards > 0){
            int select;
            while(true){
                System.out.println("Select from where you want to discard card: ");
                System.out.println("(1) Hand: " + cardInHand + " cards");
                System.out.println("(2) Active blue cards: " + activeBlueCards + " cards");
                select = KeyboardInput.readInt();
                if(select == 1 | select == 2){
                    table.discardCard(chooseCard(target, select));
                    break;
                } else{
                    System.out.println("You entered wrong number. Please try again");
                }
            }
        } else if (cardInHand > 0) {
            System.out.println("Player "+target.getName()+" dont have any active blueCards. Card will be chosen from his hand");
            table.discardCard(chooseCard(target, 1));
        }
        else {
            System.out.println("Player "+target.getName()+" dont have any cards in hand. Card will be chosen from his active cards");
            table.discardCard(chooseCard(target, 2));
        }
    }

    private void filterEnemiesWithoutCards(List<Player> enemies){
        for(Player player : enemies){
            if(player.getNumberOfCardsHand() == 0 & player.getNumberOfCardsActiveBlueCards() == 0){
                System.out.println("You cannot play Cat Balou on "+player.getName()+" because he/she doesn't have any cards.");
                enemies.remove(player);
            }
        }
    }


    private Card chooseCard(Player target, int select){
        int cardIndex;
        Card card;
        if(select == 1){
            cardIndex = random.nextInt(target.getCardsInHand().size());
            card = target.getCardsInHand().get(cardIndex);
            target.removeCardFromHand(card);
            System.out.println("Card " + card.getName() +" was discarded from "+ target.getName() + "'s hand");
        }
        else {
            cardIndex = random.nextInt(target.getActiveBlueCards().size());
            card = target.getActiveBlueCards().get(cardIndex);
            target.removeCardFromActiveBlueCards((BlueCard) card);
            System.out.println("Card " + card.getName() +" was discarded from "+ target.getName() + "'s active cards");
        }
        return card;
    }

    @Override
    public boolean isPlayable(Player currentPlayer, List<Player> enemies) {
        for(Player player : enemies){
            if(player.getCardsInHand().size() > 0 | player.getActiveBlueCards().size() > 0){
                return true;
            }
        }
        System.out.println("You cannot play Cat balou because your enemies don't have any cards.");
        return false;
    }

}
