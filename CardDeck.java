import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class CardDeck {
    ArrayList<Card> deck = new ArrayList<Card>();
    private int deckNumber;
    private File outputFile;
    private int initalSize;
    private static int numOfDecks = 0;
    public static ArrayList<CardDeck> activeCardDecks = new ArrayList<CardDeck>();

    CardDeck(){
        this.deckNumber = ++numOfDecks;
        try {
            this.outputFile = new File("deck" + deckNumber + "_output.txt");
            this.outputFile.createNewFile();
        }
        catch(IOException e){};
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
    public int getDeckSize(){return this.deck.size();}
    public void setInitialSize(int size){this.initalSize = size;}
    public int getInitialSize(){return this.initalSize;}


}
