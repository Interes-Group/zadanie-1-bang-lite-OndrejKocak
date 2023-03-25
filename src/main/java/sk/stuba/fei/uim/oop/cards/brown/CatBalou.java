package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CatBalou extends BrownCard {
    private static final String CARD_NAME = "Cat Balou";
    private final Random random;

    public CatBalou(Decks decks) {
        super(CARD_NAME, decks);
        random = new Random();
    }

    @Override
    public void play(Player playerOnTurn, List<Player> enemies) {
        super.play(playerOnTurn, enemies);
        Player target = this.selectTarget(this.filterEnemiesWithoutCards(enemies, true));
        this.decks.discardCard(chooseCard(target, selectCardSource(target)));
    }



    @Override
    public boolean isPlayable(Player playerOnTurn, List<Player> enemies, boolean excuse) {
        if (filterEnemiesWithoutCards(enemies, false).size() > 0) {
            return true;
        }
        if (excuse) {
            System.out.println(this.name + " is not playable because your enemies does not have any cards.");
        }
        return false;
    }

    private int selectCardSource(Player target) {
        int numberOfCardInHand = target.getNumberOfCardsHand();
        int numberOfCardsInFront = target.getNumberOfCardsInFront();
        int select = 0;
        if (numberOfCardInHand > 0 & numberOfCardsInFront > 0) {
            while (select != 1 && select != 2) {
                System.out.println("Select from where you want to discard card: ");
                System.out.println("(1) Hand: " + numberOfCardInHand + " cards");
                System.out.println("(2) Cards in front: " + numberOfCardsInFront + " cards");
                select = KeyboardInput.readInt();
                if (select != 1 && select != 2) {

                    System.out.println("You entered invalid number. Please try again.");
                }
            }
        } else if (numberOfCardInHand > 0) {
            System.out.println("Player " + target.getName() + " dont have any active blueCards. Card will be chosen from his hand.");
            select = 1;
        } else {
            System.out.println("Player " + target.getName() + " dont have any cards in hand. Card will be chosen from cards in front.");
            select = 2;
        }
        return select;
    }


    private Card chooseCard(Player target, int select) {
        int cardIndex;
        if (select == 1) {
            Card card;
            cardIndex = random.nextInt(target.getCardsInHand().size());
            card = target.getCardsInHand().get(cardIndex);
            target.removeCardFromHand(card);
            System.out.println("Card " + card.getName() + " was discarded from " + target.getName() + "'s hand");
            return card;
        } else {
            BlueCard card;
            cardIndex = random.nextInt(target.getCardsInFront().size());
            card = target.getCardsInFront().get(cardIndex);
            target.removeCardFromInFront(card);
            System.out.println("Card " + card.getName() + " was discarded from " + target.getName() + "'s active cards");
            return card;
        }
    }

    private List<Player> filterEnemiesWithoutCards(List<Player> enemies, boolean excuse) {
        List<Player> enemiesWithCards = new ArrayList<>();
        for (Player enemy : enemies) {
            if ((enemy.getNumberOfCardsHand() == 0) && (enemy.getNumberOfCardsInFront() == 0)) {
                if (excuse) {
                    System.out.println("You cannot play " + this.name + " on " + enemy.getName() + " because he/she doesn't have any cards.");
                }
            } else {
                enemiesWithCards.add(enemy);
            }
        }
        return enemiesWithCards;
    }
}
