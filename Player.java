import java.util.ArrayList;

public class Player{
    ArrayList<Card> hand = new ArrayList<Card>();
    private String name;
    private int number;
    private static int num_of_players = 0;
    private CardDeck draw_deck = new CardDeck();
    private CardDeck discard_deck = new CardDeck();

    Player(String name, int number){
        this.name = name;
        this.number = ++num_of_players;
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
    public CardDeck getDiscard_deck() {
        return discard_deck;
    }
    public CardDeck getDraw_deck(){
        return draw_deck;
    }
    public void setDraw_deck(CardDeck deck){
        this.draw_deck = deck;
    }
    public void setDiscard_deck(CardDeck deck){
        this.discard_deck = deck;
    }
}




