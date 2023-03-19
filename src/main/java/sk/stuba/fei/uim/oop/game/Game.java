package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player[] players;
    private Table table;
    private int currentPlayer;
    public Game() {
        System.out.println("===== Welcome to BANG! =====");
        int numberOfPlayers;
        while(true){
            numberOfPlayers = KeyboardInput.readInt("Enter the number of players (2-4)");

            if((numberOfPlayers >= 2) & (numberOfPlayers <= 4)){
                break;
            }
            else{
                System.out.println("You entered wrong number. Please enter the number from range (2-4).");
            }

        }
        this.players = new Player[numberOfPlayers];
        for(int i = 0; i < players.length; i++){
            String playerName = KeyboardInput.readString("Enter name of player " + Integer.toString(i+1));
            this.players[i] = new Player(playerName);
        }
        this.table = new Table(this.players);
        this.printSpacer();
        this.startGame();
    }

    private void startGame(){
        System.out.println("Game started");
        while(this.getNumberOfPlayersAlive() > 1){
            this.printSpacer();
            Player playerOnTurn = this.players[currentPlayer];
            System.out.println("Player " + playerOnTurn.getName() + " is starting his/her turn.");
            //TODO check activeBlueCards
            if(playerOnTurn.isAlive()){
                this.makeTurn(playerOnTurn);
            }
            incrementCounter();
        }
        Player winner = getWinner();
        System.out.println("Everyone died. Except " + winner.getName() + " who now dominates wild west.");

    }

    private void makeTurn(Player playerOnTurn){
        this.drawCards(playerOnTurn);
        List<Card> playableCards = playerOnTurn.getPlayableCards();
        System.out.println("Lives: "+ playerOnTurn.getLives());
        System.out.println("Cards in "+ playerOnTurn.getName() + "s hand:");
        this.printCards(playerOnTurn.getCardsInHand(), false);
        while(playableCards.size() > 0 & this.getNumberOfPlayersAlive() > 1){
            this.printSpacer();
            System.out.println("Do you want continue your turn?");
            System.out.println("(1) Play the card");
            System.out.println("(0) End turn");
            int continueTurn = KeyboardInput.readInt();
            if(continueTurn == 1){
                this.printSpacer();
                this.playCard(playerOnTurn, playableCards);
            } else if (continueTurn == 0) {
                this.printSpacer();
                this.discardCard(playerOnTurn);
                break;
            }
            else {
                System.out.println("You entered wrong number. Please try again");
            }
        }
    }

    private void playCard(Player playerOnTurn,List<Card> playableCards){
        int choosedCardIndex = this.chooseCard(playableCards, "play");
        this.printSpacer();
        Card choosedCard = playableCards.remove(choosedCardIndex);
        playerOnTurn.removeCardFromHand(choosedCard);
        choosedCard.play(playerOnTurn, this.getPlayersAlive(), this.table);
    }

    private void discardCard(Player playerOnTurn){
        int choosedCardIndex = 0;
        List<Card> cardsOnHand = playerOnTurn.getCardsInHand();
        while(playerOnTurn.getCardsInHand().size() > playerOnTurn.getLives()){
            this.printSpacer();
            System.out.println("You have more cards in your hand than you have lives you need to discard some.");
            choosedCardIndex = this.chooseCard(cardsOnHand, "discard");
            Card choosedCard = cardsOnHand.get(choosedCardIndex);
            playerOnTurn.removeCardFromHand(choosedCard);
            this.table.discardCard(choosedCard);
        }
    }

    private int chooseCard(List<Card> cards, String verb){
        int choosedCardIndex = 0;
        while(true){
            System.out.println("You can "+ verb + " this cards: ");
            printCards(cards, true);
            if(verb.equals("play")){
                System.out.println("(0) Cancel play");
            }
            choosedCardIndex = KeyboardInput.readInt("Enter the number of card you want to play");
            if((choosedCardIndex < 0) | (choosedCardIndex > cards.size())){
                System.out.println("You entered wrong number! Please try again");
            } else{
                break;
            }
        }
        return choosedCardIndex-1;
    }

    private void drawCards(Player playerOnTurn){
        for(int i = 0; i < 2; i++){
            playerOnTurn.takeCard(this.table.drawCard());
        }
    }


    private void printCards(List<Card> cards, boolean indexes){
        for(int i = 0; i < cards.size(); i++){
            if(indexes){
                System.out.println("("+ (i+1) + ") " + cards.get(i).getName());
            }
            else {
                System.out.println("--> " + cards.get(i).getName());
            }
        }
    }

    private void printSpacer(){
        System.out.println("=======================================");
    }

    private List<Player> getPlayersAlive(){
        List<Player> playersAlive = new ArrayList<Player>();
        for(Player player : this.players){
            if(player.isAlive()){
                playersAlive.add(player);
            }
        }
        return playersAlive;
    }
    private int getNumberOfPlayersAlive(){
        int count = 0;
        for(Player player : this.players){
            if(player.isAlive()){
                count++;
            }
        }
        return count;
    }

    private void incrementCounter(){
        this.currentPlayer++;
        this.currentPlayer = this.currentPlayer % this.players.length;
    }

    private Player getWinner(){
        for(Player player: this.players){
            if(player.isAlive()){
                return player;
            }
        }
        return null;
    }
}
