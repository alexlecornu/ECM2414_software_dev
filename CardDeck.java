import java.util.ArrayList;

public class CardDeck {
    ArrayList<Card> deck = new ArrayList<Card>();
    private int deckNumber;
    private static int numOfDecks = 0;
    public static ArrayList<CardDeck> activeCardDecks = new ArrayList<CardDeck>();

    CardDeck(){
        this.deckNumber = ++numOfDecks;
        activeCardDecks.add(this);
    }
    public void enqueue(Card card) {
        deck.add(card);
    }
    public Card dequeue(){
        return deck.remove(0);
    }
    public ArrayList<Card> getDeck(){
        return this.deck;
    }
    public int getDeckNumber(){
        return this.deckNumber;
    }


}
