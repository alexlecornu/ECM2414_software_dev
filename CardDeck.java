import java.util.ArrayList;

public class CardDeck {
    ArrayList<Card> deck = new ArrayList<Card>();
    private int deck_number;
    private static int num_of_decks = 0;

    CardDeck(){
        this.deck_number = ++num_of_decks;
    }
}