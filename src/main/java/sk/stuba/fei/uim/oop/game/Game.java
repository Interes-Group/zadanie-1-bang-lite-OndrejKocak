package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.BlueCard;
import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Prison;
import sk.stuba.fei.uim.oop.decks.Decks;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Game {
    private Player[] players;
    private Decks decks;
    private int currentPlayer;
    public Game() {
        System.out.println("===== Welcome to BANG! =====");
        this.initPlayers();
        this.printSpacer();
        this.startGame();
    }

    private void initPlayers(){
        int numberOfPlayers = 0;
        while(numberOfPlayers < 2 || numberOfPlayers > 4){
            numberOfPlayers = KeyboardInput.readInt("Enter the number of players (2-4)");

            if(numberOfPlayers < 2 || numberOfPlayers > 4){
                System.out.println("You entered invalid number. Please enter the number from range (2-4).");
            }

        }
        this.players = new Player[numberOfPlayers];
        for(int i = 0; i < players.length; i++){
            String playerName = KeyboardInput.readString("Enter name of player " + (i+1));
            this.players[i] = new Player(playerName);
        }
        this.decks = new Decks(this.players);
    }
    private void startGame(){
        System.out.println("============= Game started =============");
        while(this.getNumberOfPlayersAlive() > 1){
            this.printSpacer();
            Player playerOnTurn = this.players[currentPlayer];
            checkDynamite(playerOnTurn);
            if(playerOnTurn.isAlive() && checkPrison(playerOnTurn)){
                this.makeTurn(playerOnTurn);
            }
            incrementCounter();
        }
        Player winner = getWinner();
        System.out.println("Everyone died. Except " + winner.getName() + " who now dominates wild west.");
        System.out.println("====WINNER: "+ winner.getName()+" ====");

    }


    private void makeTurn(Player playerOnTurn){
        System.out.println("Player " + playerOnTurn.getName() + " is starting his/her turn.");
        playerOnTurn.takeCards(decks.drawCards(2));


        while(this.getNumberOfPlayersAlive() > 1){
            List<Player> enemies = playerOnTurn.getEnemies(players);
            if(playerOnTurn.getNumberOfPlayableCards(enemies) == 0){
                System.out.println("You don't have any more playable cards. Your turn is ending.");
                break;
            }
            this.printTurnInfo(playerOnTurn);

            int continueTurn = KeyboardInput.readInt();

            if(continueTurn == 1){
                this.printSpacer();
                this.playCard(playerOnTurn, enemies);
            } else if (continueTurn == 0) {
                this.printSpacer();
                this.discardCard(playerOnTurn);
                break;
            }
            else {
                System.out.println("You entered invalid number. Please try again.");
            }
            this.printSpacer();
        }
    }

    private void printTurnInfo(Player playerOnTurn){
        System.out.println("Cards in front of "+ playerOnTurn.getName()+":");
        this.printCards(playerOnTurn.getCardsInFront());
        System.out.println("Cards in "+ playerOnTurn.getName() + "'s hand:");
        this.printCards(playerOnTurn.getCardsInHand(), false);
        this.printSpacer();
        System.out.println("Lives: "+ playerOnTurn.getLives());
        this.printSpacer();
        System.out.println("Do you want continue your turn?");
        System.out.println("(1) Play the card");
        System.out.println("(0) End turn");
    }

    private void playCard(Player playerOnTurn, List<Player> enemies){
        List<Card> playableCards = playerOnTurn.getPlayableCards(enemies);
        int chosenCardIndex = this.chooseCard(playableCards, "play");
        this.printSpacer();
        if(chosenCardIndex == -1){
            return;
        }
        Card choosedCard = playableCards.get(chosenCardIndex);
        playerOnTurn.removeCardFromHand(choosedCard);
        choosedCard.play(playerOnTurn, enemies);
    }

    private void discardCard(Player playerOnTurn){
        int chosenCardIndex = 0;
        List<Card> cardsOnHand = playerOnTurn.getCardsInHand();
        int numberOfCardsAboveLives = playerOnTurn.getNumberOfCardsHand() - playerOnTurn.getLives();
        for(int i = 0; i < numberOfCardsAboveLives; i++){
            this.printSpacer();
            System.out.println("You have more cards in your hand than you have lives you need to discard "+ (numberOfCardsAboveLives-i) + " cards.");
            chosenCardIndex = this.chooseCard(cardsOnHand, "discard");
            Card choosedCard = cardsOnHand.get(chosenCardIndex);
            playerOnTurn.removeCardFromHand(choosedCard);
            this.decks.discardCard(choosedCard);
            System.out.println("You discarded " + choosedCard.getName());
        }
    }


    private int chooseCard(List<Card> cards, String verb){
        int chosenCardIndex = 0;
        while(true){
            System.out.println("You can "+ verb + " this cards: ");
            this.printCards(cards, true);
            if(verb.equals("play")){
                System.out.println("(0) Cancel play");
            }
            chosenCardIndex = KeyboardInput.readInt("Enter the number of card you want to "+ verb);
            if((chosenCardIndex < 0) | (chosenCardIndex > cards.size())){
                System.out.println("You entered invalid number! Please try again.");
            } else{
                break;
            }
        }
        return chosenCardIndex-1;
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

    private void printCards(List<BlueCard> cards){
        if(cards.size() == 0){
            System.out.println("--> NONE");
        }
        for(BlueCard card: cards){
            System.out.println("--> " + card.getName());
        }
    }

    private void printSpacer(){
        System.out.println("========================================");
    }




    private void checkDynamite(Player playerOnTurn){
        for(BlueCard card : playerOnTurn.getCardsInFront()){
            if(card instanceof Dynamite){
                if(card.checkEffect()) {
                    ((Dynamite) card).explode(playerOnTurn, this.decks);
                    decks.discardCard(card);
                }
                else {
                    System.out.println("Dynamite didn't exploded.");
                    moveDynamite(card);
                }
                playerOnTurn.removeCardFromInFront(card);
               return;
            }
        }
    }
    private void moveDynamite(BlueCard card){
        int nextPlayerIndex = currentPlayer-1;
        Player nextPlayer = null;
        while(true){
            if(nextPlayerIndex == -1){
                nextPlayerIndex = players.length-1;
            }

            nextPlayer = players[nextPlayerIndex];
            if(nextPlayer.isAlive()){
                nextPlayer.activateCard(card);
                System.out.println("Dynamite was moved to "+ nextPlayer.getName());
                break;
            }
            nextPlayerIndex--;

        }

    }

    private boolean checkPrison(Player playerOnTurn){
        for(BlueCard card : playerOnTurn.getCardsInFront()){
            if(card instanceof Prison){
                playerOnTurn.removeCardFromInFront(card);
                decks.discardCard(card);
                if(card.checkEffect()){
                    System.out.println("You escaped the prison your turn will start.");
                    return true;
                }
                else {
                    System.out.println("Player "+ playerOnTurn.getName() + " is in prison his/her turn will be skipped.");
                    return false;
                }
            }
        }
        return true;
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
