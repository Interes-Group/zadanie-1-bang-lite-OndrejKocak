package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private List<Card> cardsInHand;
    private  List<Card> activeBlueCards;
    private int lives;
    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsInHand = new ArrayList<Card>();
        this.activeBlueCards = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void addLive() {
        this.lives++;
    }

    public  void removeLive(){
        this.lives--;
    }

    public boolean isAlive(){
        return lives > 0;
    }

    public void takeCards(List<Card> cards){
        this.cardsInHand.addAll(cards);
    }

    public void removeCardFromHand(Card card){
        cardsInHand.remove(card);
    }

    public void removeCardFromActiveBlueCards(Card card){
        activeBlueCards.remove(card);
    }

    public List<Card> getPlayableCards(List<Player> enemies){
        List<Card> playableCards = new ArrayList<Card>();
        for(Card card : cardsInHand){
            if(card.isPlayable(this, enemies)){
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public List<Card> getActiveBlueCards() {
        return activeBlueCards;
    }

    public int getNumberOfCardsHand(){
        return cardsInHand.size();
    }
    public int getNumberOfCardsActiveBlueCards(){
        return activeBlueCards.size();
    }

    public int getNumberOfPlayableCards(List<Player> enemies){
        return getPlayableCards(enemies).size();
    }
}
