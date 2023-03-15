package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Table {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> discardedDeck = new ArrayList<Card>();

    public Table(Player[] players) {
        this.createDeck();
        for(Player player : players){
            for(int i = 0; i<4; i++){
                player.takeCard(this.drawCard());
            }
        }
    }

    private void createDeck(){
        for(int i = 0; i < 30; i++){
            this.deck.add(new Bang());
        }
        for(int i = 0; i < 15; i++){
            this.deck.add(new Missed());
        }
        for(int i = 0; i <8; i++){
            this.deck.add(new Beer());
        }
        Collections.shuffle(this.deck);
    }

    public Card drawCard(){
        if(deck.size() == 0){
            swapDecks();
        }
        return deck.remove(0);
    }
    public void discardCard(Card card){
        discardedDeck.add(card);
    }

    private void swapDecks(){
        ArrayList<Card> temp = this.discardedDeck;
        this.discardedDeck = this.deck;
        this.deck = temp;
        Collections.shuffle(this.deck);
    }
}
