import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// This is the Player class which is responsible for each of the player objects.
// The player class has the following attributes:
// hand - stores the current hand. Uses a dynamic structure to allow cards to be added and removed
// name - A string for the name of the player
// number - an integer for the player number
// numOfPlayers - A static integer value used to allocated the player numbers
// drawDeck - the deck object that the player draws from
// discardDeck - the deck object that the player discards cards to.
public class Player extends Thread{
    ArrayList<Card> hand = new ArrayList<Card>();
    private final String name;
    private File outputFile;
    private static volatile boolean running = true;
    private final int number;
    private static int numOfPlayers = 0;
    private CardDeck drawDeck;
    private CardDeck discardDeck;
    public static ArrayList<Player> activePlayers = new ArrayList<Player>();
    private static CyclicBarrier barrier;
    // this is the constructor of the Player class.
    // It uses the static numOfPlayers attribute to assign a player number and takes a name as an input.
    Player(){
        ++numOfPlayers;
        this.name = "player" + numOfPlayers;
        this.number = numOfPlayers;
        this.outputFile = new File(name +"_output.txt");
        //overwrites current output file
        try (FileWriter overwrite = new FileWriter(outputFile, false)) {
        } catch (IOException e) {};
        activePlayers.add(this);
    }
    // These are some of the methods of the class.
    // They are used for getting and setting attributes not defined in the constructor.
    public String getPlayerName() {return name;}
    public int getNumber() {return number;}
    public ArrayList<Card> getHand() {return hand;}
    public CardDeck getDiscardDeck() {return discardDeck;}
    public CardDeck getDrawDeck(){return drawDeck;}
    public void setDrawDeck(CardDeck deck){this.drawDeck = deck;}

    public static void setBarrier(){
        barrier = new CyclicBarrier(numOfPlayers);
    }

    public static void writeInitialHands() {
        for (Player player: activePlayers){
            String writeMsg = "player " + player.getNumber() + " initial hand " + player.handToStr();
            player.writeToFile(writeMsg);
        }
    }

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

    public void setDiscardDeck(CardDeck deck){this.discardDeck = deck;}

    public void addCard(Card card){this.hand.add(card);}

    public void removeCard(Card card){this.hand.remove(card);}

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


    public synchronized void checkWinState(){
    int firstVal = this.hand.get(0).getValue();
    boolean allEqual = true;
    for (Card card: this.hand){
        if (firstVal != card.getValue()) {
            allEqual = false;
            break;
        }
    }
    if (allEqual){
        String winMsg = ("player " + this.getNumber() + " wins");
        System.out.println(winMsg);
        this.writeToFile(winMsg);
        running = false;
        for (Player player : activePlayers){
            player.interrupt();
            if (player != this){
                String notWinMsg = ("player "+this.getNumber()+" has informed "+" player "+player.getNumber()+ " that player " + this.getNumber() + " has won");
                player.writeToFile(notWinMsg);
            }
            String exitMsg = ("player " + player.getNumber() + " exits");
            String handMsg = ("player " + player.getNumber() + " hand: " + player.handToStr());
            player.writeToFile(exitMsg);
            player.writeToFile(handMsg);
            }
        for (CardDeck deck : CardDeck.activeCardDecks){
            deck.writeToFile();
        }
        }

    }

    protected synchronized void  drawAndDiscard() {
        while (this.drawDeck.getDeckSize() < this.drawDeck.getInitialSize()){
            continue;
        }
        Card cardToDraw = this.drawDeck.dequeue();
        String drawMsg = ("player " + this.getNumber() + " draws a " + cardToDraw.getValue() + " from deck " + this.drawDeck.getDeckNumber());
        this.addCard( cardToDraw );
        this.writeToFile(drawMsg);

        for (Card card: this.hand){
            if (card.getValue() != this.getNumber() ) {
                this.removeCard(card);
                this.discardDeck.enqueue(card);
                String discardMsg = ("player " + this.getNumber() + " discards a " + card.getValue() + " to deck " + this.discardDeck.getDeckNumber());
                this.writeToFile(discardMsg);
                break;
            }
        }}

    private void writeToFile(String message) {
        try {
            FileWriter writer = new FileWriter(this.outputFile,true);
            writer.write(message);
            writer.write("\n");
            writer.close();
        }
        catch(IOException e){}

    }


    @Override
    public void run() {
        while (running) {
            try{barrier.await();}
            catch(InterruptedException e){break;}
            catch(BrokenBarrierException e){}
            this.checkWinState();

            if (!running) {
                break;
            }

            this.drawAndDiscard();
            String handMsg = ("player " + this.getNumber() + " current hand is " + this.handToStr());
            this.writeToFile(handMsg);
            try{barrier.await();}
            catch(InterruptedException e){break;}
            catch(BrokenBarrierException e){}
        }
        }
    }





