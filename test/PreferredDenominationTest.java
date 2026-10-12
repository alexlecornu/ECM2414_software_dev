import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PreferredDenominationTest {

    static Player p1;
    static CardDeck dr;
    static CardDeck dis;

    @BeforeAll
    public static void setup(){
        // create a new player and a draw and discard Deck
        Player p1 = new Player();
        CardDeck dr = new CardDeck();
        CardDeck dis = new CardDeck();
        p1.setDrawDeck(dr);
        p1.setDiscardDeck(dis);
        // create the cards to assign to the player. As there is only one player their preferred number will be 1

        // these first cards will be assigned to the players hand
        Card c1 = new Card(1);
        Card c2 = new Card(2);
        Card c3 = new Card(1);
        Card c4 = new Card(2);
        p1.addCard(c1);
        p1.addCard(c2);
        p1.addCard(c3);
        p1.addCard(c4);

        //these cards will be added to the Card deck
        Card c5 = new Card(3);
        Card c6 = new Card(2);
        Card c7 = new Card(1);
        Card c8 = new Card(1);
        dr.enqueue(c5);
        dr.enqueue(c6);
        dr.enqueue(c7);
        dr.enqueue(c8);

    }
    @Test
    public void checkDiscard(){
        p1.drawAndDiscard();
    }
}
