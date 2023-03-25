package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cardsInHand;
    private final List<BlueCard> cardsInFront;
    private int lives;

    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsInHand = new ArrayList<>();
        this.cardsInFront = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public List<Player> getEnemies(Player[] players) {
        List<Player> playersAlive = new ArrayList<>();
        for (Player player : players) {
            if (player.isAlive() && !player.equals(this)) {
                playersAlive.add(player);
            }
        }
        return playersAlive;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public List<BlueCard> getCardsInFront() {
        return cardsInFront;
    }

    public int getNumberOfCardsHand() {
        return cardsInHand.size();
    }

    public int getNumberOfCardsInFront() {
        return cardsInFront.size();
    }

    public List<Card> getPlayableCards(List<Player> enemies, boolean excuse) {
        List<Card> playableCards = new ArrayList<>();
        for (Card card : cardsInHand) {
            if (card.isPlayable(this, enemies, excuse)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    public int getNumberOfPlayableCards(List<Player> enemies) {
        return getPlayableCards(enemies, false).size();
    }

    public void addLive() {
        this.lives++;
    }


    public void removeLives(int numberOfLives) {
        this.lives -= numberOfLives;
        if (lives < 0) {
            lives = 0;
        }
    }


    public void takeCards(List<Card> cards) {
        this.cardsInHand.addAll(cards);
    }

    public void activateCard(BlueCard card) {
        cardsInFront.add(card);
    }

    public void removeCardFromInFront(BlueCard card) {
        cardsInFront.remove(card);
    }

    public void removeCardFromHand(Card card) {
        cardsInHand.remove(card);
    }


    public boolean isAlive() {
        return lives > 0;
    }

    public List<Card> die() {
        List<Card> allCards = new ArrayList<>(cardsInHand);
        cardsInHand.clear();
        allCards.addAll(cardsInFront);
        cardsInFront.clear();
        return allCards;
    }
}
