package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;

import java.util.ArrayList;

public class Player {
    private final String name;
    private ArrayList<Card> cardsInHand;
    private  ArrayList<Card> activeBlueCards;
    private int lives;
    public Player(String name) {
        this.name = name;
        this.lives = 4;
        this.cardsInHand = new ArrayList<Card>();
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
        if(lives > 0){
            return true;
        }
        return false;
    }

    public void takeCard(Card card){
        cardsInHand.add(card);
    }

    public void printCards(){
        System.out.println("Cards in "+ this.name + "'s hand: ");
        for(Card card : cardsInHand){
            System.out.println(card.getName());
        }
    }

    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }

    public ArrayList<Card> getActiveBlueCards() {
        return activeBlueCards;
    }
}
