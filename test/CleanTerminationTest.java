import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CleanTerminationTest {
    @BeforeEach
    public void reset(){
        Player.resetForTests();
        CardDeck.resetForTests();
        Card.resetForTests();
    }

    @Test
    public void CleanTermination() throws InterruptedException {
        //tests whether player 1 winning after one draw and discard terminates cleanly
        //initial set up of objects
        Player p = new Player();
        Player p2 = new Player();
        CardDeck dr = new CardDeck();
        CardDeck dis = new CardDeck();
        p.setDiscardDeck(dis);
        p.setDrawDeck(dr);
        p2.setDiscardDeck(dr);
        p2.setDrawDeck(dis);
        //player one needs one more one to win the game
        Card c1 = new Card(1);
        Card c2 = new Card(1);
        Card c3 = new Card(1);
        Card c4 = new Card(2);

        Card c5 = new Card(5);
        Card c6 = new Card(6);
        Card c7 = new Card(7);
        Card c8 = new Card(8);

        // this card is added to the front of player 1's draw deck, player 1 draws this on its first turn
        Card c9 = new Card(1);
        Card c10 = new Card(10);
        Card c11 = new Card(11);
        Card c12 = new Card(12);
        p.addCard(c1);
        p.addCard(c2);
        p.addCard(c3);
        p.addCard(c4);
        p2.addCard(c5);
        p2.addCard(c6);
        p2.addCard(c7);
        p2.addCard(c8);
        p.getDrawDeck().enqueue(c9);
        p.getDrawDeck().enqueue(c10);
        p2.getDrawDeck().enqueue(c11);
        p2.getDrawDeck().enqueue(c12);

        Player.setBarrier();
        p.start();
        p2.start();
        p.join();
        p2.join();

        // asserts whether threads stop running safely
        Assertions.assertTrue(p.isInterrupted());
        Assertions.assertTrue(p2.isInterrupted());

        // asserts whether deck files have been made
        File deck1Out = new File("deck1_output.txt");
        File deck2Out = new File("deck2_output.txt");
        assertTrue(deck1Out.length() > 0);
        assertTrue(deck2Out.length() > 0 );

        // asserts whether player files have been created
        File player1Out = new File("player1_output.txt");
        File player2Out = new File("player2_output.txt");
        assertTrue(player1Out.length() > 0);
        assertTrue(player2Out.length() > 0 );
    }
}
