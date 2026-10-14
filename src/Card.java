import java.util.ArrayList;

public class Card {

    public static ArrayList<Card> pack = new ArrayList<Card>();
    private int value;

    // constructor
    Card(int value ){
        this.value = value;
        pack.add(this);
    }

    public int getValue(){
        return this.value;
    }

    // testing methods
    public static void resetForTests(){
        pack.clear();
    }
}