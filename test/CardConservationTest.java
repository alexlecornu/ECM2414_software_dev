import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardConservationTest {
    @BeforeEach
    public void reset(){
        Player.resetForTests();
        CardDeck.resetForTests();
        Card.resetForTests();
    }

    @Test
    public void ConservationBetweenDrawAndDiscard(){
        // tests whether a card drawn moves successfully
        Player p = new Player();
        Player p2 = new Player();
        CardDeck dr = new CardDeck();
        CardDeck dis = new CardDeck();
        p.setDiscardDeck(dis);
        p.setDrawDeck(dr);
        p2.setDiscardDeck(dr);
        p2.setDrawDeck(dis);
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
        p2.getDrawDeck().enqueue(c7);
        p2.getDrawDeck().enqueue(c8);
        p.addCard(c1);
        p.addCard(c2);
        p2.addCard(c3);
        p2.addCard(c4);
        // calls the draw and discard method
        p.drawAndDiscard();
        p2.drawAndDiscard();
        // Checks that both players have the correct hand size, meaning cards are being conserved
        Assertions.assertEquals(p.getHand().size(),2);
        Assertions.assertEquals(p2.getHand().size(),2);

        // checks that all decks maintain the right number of cards, meaning cards are being conserved
        Assertions.assertEquals(dr.getDeckSize(),2);
        Assertions.assertEquals(dis.getDeckSize(),2);

    }
    
}
