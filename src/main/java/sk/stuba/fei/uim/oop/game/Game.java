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
        System.out.println("#####Welcome to BANG!#####");
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
        this.startGame();
    }

    private void startGame(){
        System.out.println("Game started");
        while(this.getNumberOfPlayersAlive() > 1){
            Player playerOnTurn = this.players[currentPlayer];
            if(playerOnTurn.isAlive()){
                this.makeTurn(playerOnTurn);
            }
            break;
        }
    }

    private void makeTurn(Player playerOnTurn){
        //TODO check activeBlueCards
        ArrayList<Card> playableCards = playerOnTurn.getCardsInHand();
        while(playableCards.size() > 0){
            this.playCard(playableCards);
            System.out.println("Do you want continue turn? (yes/no)");
            if(!KeyboardInput.readBoolean()){
                break;
            }
        }
    }

    private void playCard(ArrayList<Card> playableCards){

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
