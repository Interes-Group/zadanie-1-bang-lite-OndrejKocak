package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;
import java.util.Random;

public class CatBalou extends BrownCard {
    private static final String CARD_NAME="Cat Balou";
    private final Random random;
    public CatBalou(Decks decks) {
        super(CARD_NAME, decks);
        random = new Random();
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        this.filterEnemiesWithoutCards(enemies);
        Player target = super.selectTarget(enemies);
        int numberOfCardInHand = target.getNumberOfCardsHand();
        int numberOfCardsInFront = target.getNumberOfCardsInFront();
        if(numberOfCardInHand > 0 & numberOfCardsInFront > 0){
            int select;
            while(true){
                System.out.println("Select from where you want to discard card: ");
                System.out.println("(1) Hand: " + numberOfCardInHand + " cards");
                System.out.println("(2) Active blue cards: " + numberOfCardsInFront + " cards");
                select = KeyboardInput.readInt();
                if(select == 1 | select == 2){
                    this.decks.discardCard(chooseCard(target, select));
                    break;
                } else{
                    System.out.println("You entered wrong number. Please try again");
                }
            }
        } else if (numberOfCardInHand > 0) {
            System.out.println("Player "+target.getName()+" dont have any active blueCards. Card will be chosen from his hand");
            this.decks.discardCard(chooseCard(target, 1));
        }
        else {
            System.out.println("Player "+target.getName()+" dont have any cards in hand. Card will be chosen from his active cards");
            this.decks.discardCard(chooseCard(target, 2));
        }
    }

    private void filterEnemiesWithoutCards(List<Player> enemies){
        for(Player player : enemies){
            if(player.getNumberOfCardsHand() == 0 & player.getNumberOfCardsInFront() == 0){
                System.out.println("You cannot play "+this.name + " on "+player.getName()+" because he/she doesn't have any cards.");
                enemies.remove(player);
            }
        }
    }


    private Card chooseCard(Player target, int select){
        int cardIndex;
        if(select == 1){
            Card card;
            cardIndex = random.nextInt(target.getCardsInHand().size());
            card = target.getCardsInHand().get(cardIndex);
            target.removeCardFromHand(card);
            System.out.println("Card " + card.getName() +" was discarded from "+ target.getName() + "'s hand");
            return card;
        }
        else {
            BlueCard card;
            cardIndex = random.nextInt(target.getCardsInFront().size());
            card = target.getCardsInFront().get(cardIndex);
            target.removeCardFromInFront(card);
            System.out.println("Card " + card.getName() +" was discarded from "+ target.getName() + "'s active cards");
            return card;
        }
    }

    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies) {
        for(Player player : enemies){
            if(player.getCardsInHand().size() > 0 | player.getCardsInFront().size() > 0){
                return true;
            }
        }
        System.out.println("You cannot play Cat balou because your enemies don't have any cards.");
        return false;
    }

}
