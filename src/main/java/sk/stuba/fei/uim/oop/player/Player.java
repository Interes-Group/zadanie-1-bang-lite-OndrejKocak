package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cardsInHand;
    private  final List<BlueCard> activeBlueCards;
    private int lives;
    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsInHand = new ArrayList<>();
        this.activeBlueCards = new ArrayList<>();
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

    public void removeLive(int n){
        this.lives -= n;
        if(lives < 0){
            lives = 0;
        }
    }

    public boolean isAlive(){
        return lives > 0;
    }

    public void takeCards(List<Card> cards){
        this.cardsInHand.addAll(cards);
    }

    public void activateCard(BlueCard card){
        activeBlueCards.add(card);
    }

    public void removeCardFromActive(BlueCard card){
        activeBlueCards.remove(card);
    }

    public void removeCardFromHand(Card card){
        cardsInHand.remove(card);
    }

    public void removeCardFromActiveBlueCards(BlueCard card){
        activeBlueCards.remove(card);
    }

    public List<Card> getPlayableCards(List<Player> enemies){
        List<Card> playableCards = new ArrayList<>();
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

    public List<BlueCard> getActiveBlueCards() {
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

    public List<Card> die(){
        List<Card> allCards = new ArrayList<>(cardsInHand);
        cardsInHand.clear();
        allCards.addAll(activeBlueCards);
        activeBlueCards.clear();
        return allCards;
    }
}
