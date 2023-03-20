package sk.stuba.fei.uim.oop.table;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> discardedDeck = new ArrayList<Card>();

    public Table(Player[] players) {
        this.createDeck();
        for(Player player : players){
            player.takeCards(this.drawCards(4));
        }
    }

    private void createDeck(){
        for(int i = 0; i < 30; i++){
            this.deck.add(new Bang());
        }
        for(int i = 0; i < 15; i++){
            this.deck.add(new Missed());
        }
        for(int i = 0; i < 8; i++){
            this.deck.add(new Beer());
        }
        for(int i = 0; i < 4; i++){
            this.deck.add(new Stagecoach());
        }
        for(int i = 0; i < 2; i++){
            this.deck.add(new Indians());
        }
        for(int i = 0; i < 6; i++){
            this.deck.add(new CatBalou());
        }
        Collections.shuffle(this.deck);
    }

    public List<Card> drawCards(int n){
        List<Card> cards = new ArrayList<Card>();
        for(int i = 0; i < n;i++) {
            if (deck.size() == 0) {
                swapDecks();
            }
            cards.add(this.deck.remove(0));
        }
        return cards;
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
