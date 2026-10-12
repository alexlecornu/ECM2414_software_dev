import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class InstantWinTest {

    @Test
    public void instantWinTest(){
        // creates a player and 4 cards which all have the same value
        Player p1 = new Player();
        Card c1 = new Card(1);
        Card c2 = new Card(1);
        Card c3 = new Card(1);
        Card c4 = new Card(1);
        // assigns all of the cards to the players hand
        p1.addCard(c1);
        p1.addCard(c2);
        p1.addCard(c3);
        p1.addCard(c4);
        p1.setBarrier();
        // starts the player thread
        p1.start();
        // check the output file
    }
}
