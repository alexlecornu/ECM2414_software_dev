import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DrawAndDiscardTest {
    @BeforeEach
    public void reset(){
        Player.resetForTests();
        CardDeck.resetForTests();
        Card.resetForTests();
    }


    @Test
    public void handLength(){
        // creates the player object
        // creates the draw deck and discard deck
        // creates card objects and assigns them to the players hand and the draw/discard decks
        Player p = new Player();
        CardDeck dr = new CardDeck();
        CardDeck dis = new CardDeck();
        p.setDiscardDeck(dis);
        p.setDrawDeck(dr);
        Card c1 = new Card(5);
        Card c2 = new Card(6);
        Card c3 = new Card(7);
        Card c4 = new Card(8);
        Card c5 = new Card(5);
        Card c6 = new Card(6);
        Card c7 = new Card(7);
        Card c8 = new Card(8);
        // adds the card objects to the draw deck and player hand
        p.getDrawDeck().enqueue(c5);
        p.getDrawDeck().enqueue(c6);
        p.getDrawDeck().enqueue(c7);
        p.getDrawDeck().enqueue(c8);
        p.addCard(c1);
        p.addCard(c2);
        p.addCard(c3);
        p.addCard(c4);
        // calls the draw and discard method
        p.drawAndDiscard();

        // Checks that the size of the hand is 4
        Assertions.assertEquals(p.getHand().size(),4);
        // checks that a card has been removed from the draw deck
        Assertions.assertEquals(p.getDrawDeck().getDeckSize(),3);
        // checks that a card has been added to the discard deck
        Assertions.assertEquals(p.getDiscardDeck().getDeckSize(), 1);

    }
}
