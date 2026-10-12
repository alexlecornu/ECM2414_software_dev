import java.io.FileWriter;
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
        this.outputFile = new File("deck" + deckNumber + "_output.txt");
        try (FileWriter overwrite = new FileWriter(outputFile, false)) {
        } catch (IOException e) {};

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

    public void writeToFile(){
        StringBuilder deckStr = new StringBuilder();
        for (Card card: this.getDeck()){
            deckStr.append(card.getValue());
            if (card == this.getDeck().getLast()){
                continue;
            }
            deckStr.append(" ");
        }
        String deckMsg = ("deck"+this.getDeckNumber()+ " contents: "+deckStr);
        try {
            FileWriter writer = new FileWriter(this.outputFile, true);
            writer.write(deckMsg);
            writer.write("\n");
            writer.close();
        }
        catch(IOException e){}
    }}


