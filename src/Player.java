import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// extends thread so each player is runnable as its own thread
public class Player extends Thread{
    public static ArrayList<Player> activePlayers = new ArrayList<Player>();
    // barrier used to make sure all threads reach certain stages before the program continues
    private static CyclicBarrier barrier;
    // volatile so every thread can see when a player thread has stopped running
    private static volatile boolean running = true;
    private final int number;
    private static int numOfPlayers = 0;
    private final String name;
    private File outputFile;
    private CardDeck drawDeck;
    private CardDeck discardDeck;
    ArrayList<Card> hand = new ArrayList<Card>();

    //constructor
    Player(){
        ++numOfPlayers;
        this.name = "player" + numOfPlayers;
        this.number = numOfPlayers;
        this.outputFile = new File(name +"_output.txt");
        // opens file to overwrite previous contents from previous games
        try (FileWriter overwrite = new FileWriter(outputFile, false)) {
        } catch (IOException e) {};
        activePlayers.add(this);
    }

    // get methods
    public String getPlayerName() {return name;}
    public int getNumber() {return number;}
    public ArrayList<Card> getHand() {return hand;}
    public CardDeck getDiscardDeck() {return discardDeck;}
    public CardDeck getDrawDeck(){return drawDeck;}

    // set methods
    public static void setBarrier(){ barrier = new CyclicBarrier(numOfPlayers);}
    public void setDrawDeck(CardDeck deck){this.drawDeck = deck;}
    public void setDiscardDeck(CardDeck deck){this.discardDeck = deck;}

    public void addCard(Card card){this.hand.add(card);}
    public void removeCard(Card card){this.hand.remove(card);}

    // writes each players starting hands to their respective txt files
    public static void writeInitialHands() {
        for (Player player: activePlayers){
            String writeMsg = "player " + player.getNumber() + " initial hand " + player.handToStr();
            player.writeToFile(writeMsg);
        }
    }

    // formats hand into the right format for writing them to a txt file
    private StringBuilder handToStr() {
        StringBuilder handStr = new StringBuilder();
        for (Card card: hand){
            handStr.append(card.getValue());
            if (card == this.hand.getLast()){
                continue;
            }
            handStr.append(" ");
        }
        return handStr;
    }

    // general purpose method used to write to the players output file
    private void writeToFile(String message) {
        try {
            FileWriter writer = new FileWriter(this.outputFile,true);
            writer.write(message);
            writer.write("\n");
            writer.close();
        }
        catch(IOException e){}

    }

    public static void deal(ArrayList<Card> pack, ArrayList<CardDeck> activeCardDecks){
        for (int i = 1; i < 5; i++){
            for (int j = 0; j < activePlayers.size(); j++ ){
                Card card = pack.remove(0);
                activePlayers.get(j).addCard(card);
            }
        }
        while (pack.size() > 0){

            for (int i=0;i<activeCardDecks.size();i++){
                Card card = pack.remove(0);
                activeCardDecks.get(i).enqueue(card);
            }
        }
        for (CardDeck deck : activeCardDecks) {
            deck.setInitialSize(deck.getDeckSize());
        }
    }

    // draws and discards a card, synchronized so its treated as one atomic action
    protected synchronized void  drawAndDiscard() {
        // waits until deck has the right number of cards from players discarding before drawing
        while (this.drawDeck.getDeckSize() < this.drawDeck.getInitialSize()){
            continue;
        }
        // dequeues card from draw deck
        Card cardToDraw = this.drawDeck.dequeue();
        String drawMsg = ("player " + this.getNumber() + " draws a " + cardToDraw.getValue() + " from deck " + this.drawDeck.getDeckNumber());
        // adds card to hand and appends to player file
        this.addCard( cardToDraw );
        this.writeToFile(drawMsg);

        // discard mechanism works by discarding the first card that isnt preferable
        for (Card card: this.hand){
            if (card.getValue() != this.getNumber() ) {
                // removes card from hand and adds its to the players discard deck
                this.removeCard(card);
                this.discardDeck.enqueue(card);
                String discardMsg = ("player " + this.getNumber() + " discards a " + card.getValue() + " to deck " + this.discardDeck.getDeckNumber());
                this.writeToFile(discardMsg);
                break;
            }
        }}


    // checks win state of a player and ends the game if necessary
    public synchronized void checkWinState(){
    int firstVal = this.hand.get(0).getValue();
    boolean allEqual = true;
    // checks if all cards are equal
    for (Card card: this.hand){
        if (firstVal != card.getValue()) {
            allEqual = false;
            break;
        }
    }
    // if the player has won - proceeds with ending the game
    if (allEqual){
        String winMsg = ("player " + this.getNumber() + " wins");
        System.out.println(winMsg);
        this.writeToFile(winMsg);
        // stops running current player
        running = false;
        // interrupts every other player
        for (Player player : activePlayers){
            player.interrupt();
            if (player != this){
                // writes to output file
                String notWinMsg = ("player "+this.getNumber()+" has informed "+" player "+player.getNumber()+ " that player " + this.getNumber() + " has won");
                player.writeToFile(notWinMsg);
            }
            // writes to output file
            String exitMsg = ("player " + player.getNumber() + " exits");
            String handMsg = ("player " + player.getNumber() + " hand: " + player.handToStr());
            player.writeToFile(exitMsg);
            player.writeToFile(handMsg);
            }
        // writes card deck txt files
        for (CardDeck deck : CardDeck.activeCardDecks){
            deck.writeToFile();
        }
        }

    }


    // main method for running the player threads
    @Override
    public void run() {
        // continues looping while game hasnt ended
        while (running) {
            // waits for every player to run before rerunning
            //try{barrier.await();}
            //catch(InterruptedException e){break;}
            //catch(BrokenBarrierException e){}
            // checks if the player has won
            this.checkWinState();

            // breaks out of loop if player has won to stop execution of next methods
            if (!running) {
                break;
            }

            // draws and discards
            this.drawAndDiscard();
            // writes current hand to txt file
            String handMsg = ("player " + this.getNumber() + " current hand is " + this.handToStr());
            this.writeToFile(handMsg);
            // waits for every player to draw and discard before continuing
            try{barrier.await();}
            catch(InterruptedException e){break;}
            catch(BrokenBarrierException e){}
        }
        }
    }





