import java.util.ArrayList;

public class CardDeck {
    ArrayList<Card> deck = new ArrayList<Card>();
    private int deckNumber;
    private static int numOfDecks = 0;

    CardDeck(){
        this.deckNumber = ++numOfDecks;
    }
    public void enqueue(Card card) {
        deck.add(card);
    }
    public Card dequeue(){
        return deck.remove(0);
    }
}
