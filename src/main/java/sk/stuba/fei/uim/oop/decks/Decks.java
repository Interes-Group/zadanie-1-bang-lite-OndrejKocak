package sk.stuba.fei.uim.oop.decks;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.cards.brown.*;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Decks {
    private final ArrayList<Card> deck = new ArrayList<>();
    private final ArrayList<Card> discardedDeck = new ArrayList<>();

    public Decks(Player[] players) {
        this.createDeck();
        for(Player player : players){
            player.takeCards(this.drawCards(4));
        }
    }

    private void createDeck(){
        for(int i = 0; i < 30; i++){
            this.deck.add(new Bang(this));
        }
        for(int i = 0; i < 15; i++){
            this.deck.add(new Missed(this));
        }
        for(int i = 0; i < 8; i++){
            this.deck.add(new Beer(this));
        }
        for(int i = 0; i < 4; i++){
            this.deck.add(new Stagecoach(this));
        }
        for(int i = 0; i < 2; i++){
            this.deck.add(new Indians(this));
        }
        for(int i = 0; i < 6; i++){
            this.deck.add(new CatBalou(this));
        }
        for(int i = 0; i < 2; i++){
            this.deck.add(new Barrel());
        }
        for(int i = 0; i < 3; i++){
            this.deck.add(new Prison());
        }
        this.deck.add(new Dynamite());
        Collections.shuffle(this.deck);
    }

    public List<Card> drawCards(int numberOfCards){
        List<Card> cards = new ArrayList<>();
        for(int i = 0; i < numberOfCards;i++) {
            if(getNumberOfAvailableCards() == 0){
                System.out.println("There is not enough card in the deck you drawn " + (i+1) + " card/s.");
                break;
            }
            else if (deck.size() == 0) {
                swapDecks();
            }
            cards.add(this.deck.remove(0));
        }
        return cards;
    }
    public void discardCard(Card card){
        discardedDeck.add(card);
    }

    public void discardCards(List<Card> cards){
        discardedDeck.addAll(cards);
    }

    public int getNumberOfAvailableCards(){
        return deck.size()+discardedDeck.size();
    }

    private void swapDecks(){
        this.deck.addAll(discardedDeck);
        this.discardedDeck.clear();
        Collections.shuffle(this.deck);
    }
}
