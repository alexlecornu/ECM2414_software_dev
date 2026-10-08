import java.util.ArrayList;
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
    private String name;
    private int number;
    private static int numOfPlayers = 0;
    private CardDeck drawDeck;
    private CardDeck discardDeck;
    public static ArrayList<Player> activePlayers = new ArrayList<Player>();
    // this is the constructor of the Player class.
    // It uses the static numOfPlayers attribute to assign a player number and takes a name as an input.
    Player(){
        ++numOfPlayers;
        this.name = "player" + numOfPlayers;
        this.number = numOfPlayers;
        activePlayers.add(this);
    }
    // These are some of the methods of the class.
    // They are used for getting and setting attributes not defined in the constructor.
    public String getPlayerName() {return name;}
    public int getNumber() {return number;}
    public ArrayList<Card> getHand() {return hand;}
    public void setPlayerName(String name){this.name = name;}
    public CardDeck getDiscardDeck() {return discardDeck;}
    public CardDeck getDrawDeck(){return drawDeck;}
    public void setDrawDeck(CardDeck deck){this.drawDeck = deck;}

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
    }

    public boolean hasWon(){
    int firstVal = this.hand.get(0).getValue();
    for (Card card: this.hand){
        if (firstVal != card.getValue()) {
            return false;}
    }
    return true;
    }

    private void drawAndDiscard() {
        this.addCard( this.drawDeck.dequeue() );

        for (Card card: this.hand){
            if (card.getValue() != this.getNumber() ) {
                this.removeCard(card);
                this.discardDeck.enqueue(card);}
                break;
        }
    }


    @Override
    public void run(){
    //check if player has won initially
    //Draw and discard from decks in one atomic action
    // check if player has won}
} }




