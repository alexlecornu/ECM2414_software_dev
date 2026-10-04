import java.util.ArrayList;

public class Player{
    ArrayList<Card> hand = new ArrayList<Card>();
    private String name;
    private int number;
    private static int numOfPlayers = 0;
    private CardDeck drawDeck = new CardDeck();
    private CardDeck discardDeck = new CardDeck();

    Player(String name, int number){
        this.name = name;
        this.number = ++numOfPlayers;
    }

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
}




