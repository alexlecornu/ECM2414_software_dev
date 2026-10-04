import java.util.ArrayList;
// This is the Card class.
// Each card is its own object which has a card value attribute representing the number on the card.
public class Card {
    private int value;
    static ArrayList<Card> pack = new ArrayList<Card>();
    // this is the construstor of the card class used to make a new card.
    Card(int value ){
        this.value = value;
        pack.add(this);
    }
    //this is the get value method of the class used to determine the value of the card
    public int getValue(){
        return this.value;
    }
}