import java.util.ArrayList;

public class CardDeck {
    ArrayList<Card> deck = new ArrayList<Card>();
    private int deckNumber;
    private static int numOfDecks = 0;

    CardDeck(){
        this.deckNumber = ++numOfDecks;
    }
}