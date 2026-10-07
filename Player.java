import java.util.ArrayList;
// This is the Player class which is responsible for each of the player objects.
// The player class has the following attributes:
// hand - stores the current hand. Uses a dynamic structure to allow cards to be added and removed
// name - A string for the name of the player
// number - an integer for the player number
// numOfPlayers - A static integer value used to allocated the player numbers
// drawDeck - the deck object that the player draws from
// discardDeck - the deck object that the player discards cards to.
public class Player{
    ArrayList<Card> hand = new ArrayList<Card>();
    private String name;
    private int number;
    private static int numOfPlayers = 0;
    private CardDeck drawDeck = new CardDeck();
    private CardDeck discardDeck = new CardDeck();
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
    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    public void setName(String name){
        this.name = name;
    }
    public CardDeck getDiscardDeck() {
        return discardDeck;
    }
    public CardDeck getDrawDeck(){
        return drawDeck;
    }
    public void setDrawDeck(CardDeck deck){
        this.drawDeck = deck;
    }
    public void setDiscardDeck(CardDeck deck){
        this.discardDeck = deck;
    }
    public void recieveCard(Card card){
        this.hand.add(card);
    }
    public static void deal(ArrayList<Card> pack){
        for (int i = 1; i < 5; i++){
            for (int j = 0; j < activePlayers.size(); j++ ){
                Card newCard = pack.remove(0);
                activePlayers.get(j).recieveCard(newCard);
            }
        }

    }
}




