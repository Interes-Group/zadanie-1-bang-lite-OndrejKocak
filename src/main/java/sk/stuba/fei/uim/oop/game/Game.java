package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.table.Table;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

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
        printSpacer();
        this.startGame();
    }

    private void startGame(){
        System.out.println("Game started");
        while(this.getNumberOfPlayersAlive() > 1){
            printSpacer();
            Player playerOnTurn = this.players[currentPlayer];
            System.out.println("Player " + playerOnTurn.getName() + " is starting his turn.");
            //TODO check activeBlueCards
            if(playerOnTurn.isAlive()){
                this.makeTurn(playerOnTurn);
            }
            break;
        }
    }

    private void makeTurn(Player playerOnTurn){
        drawCards(playerOnTurn);
        List<Card> playableCards = playerOnTurn.getPlayableCards();
        System.out.println("Cards in "+ playerOnTurn.getName() + "s hand:");
        printCards(playerOnTurn.getCardsInHand());
        while(playableCards.size() > 0){
            System.out.println("Do you want play card? (yes/no)");
            if(!KeyboardInput.readBoolean()){
                break;
            }
            this.playCard(playerOnTurn, playableCards);
        }
    }

    private void playCard(Player playerOnTurn,List<Card> playableCards){
        int choosedCardIndex = 0;
        while(true){
            System.out.println("You can play this cards: ");
            printCards(playableCards);
            System.out.println("(0) Cancel play");
            choosedCardIndex = ZKlavesnice.readInt("Enter the number of card you want to play:");

            if((choosedCardIndex < 0) | (choosedCardIndex > playableCards.size())){
                System.out.println("You entered wrong number! Please try again");
            } else if (choosedCardIndex == 0) {
                return;
            } else{
                break;
            }
        }
        Card choosedCard = playableCards.remove(choosedCardIndex - 1);
        choosedCard.play(playerOnTurn, this.getPlayersAlive());
        this.table.discardCard(choosedCard);
    }

    private void drawCards(Player playerOnTurn){
        for(int i = 0; i < 2; i++){
            playerOnTurn.takeCard(this.table.drawCard());
        }
    }

    private void printCards(List<Card> cards){
        for(int i = 0; i < cards.size(); i++){
            System.out.println("("+ (i+1) + ") " + cards.get(i).getName());
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
}
