
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class testEnqueueAndDequeue {
    @BeforeEach
    public void reset(){
        Player.resetForTests();
        CardDeck.resetForTests();
        Card.resetForTests();
    }

    @Test
    public void enqueueTest(){
        // creating the cards and adding cards to the card deck
        Card c1 = new Card(5);
        Card c2 = new Card(6);
        Card c3 = new Card(7);
        Card c4 = new Card(8);
        CardDeck d = new CardDeck();
        d.enqueue(c1);
        d.enqueue(c2);
        d.enqueue(c3);
        d.enqueue(c4);
        // checks the first value in the deck to confirm that the card deck behaves like a queue
        Assertions.assertEquals(c1,d.getDeck().getFirst());
    }
    @Test
    public void enqueueTest2(){
        Card c1 = new Card(5);
        Card c2 = new Card(6);
        Card c3 = new Card(7);
        Card c4 = new Card(8);
        CardDeck d = new CardDeck();
        d.enqueue(c1);
        d.enqueue(c2);
        d.enqueue(c3);
        d.enqueue(c4);
        // checks the last value in the deck to confirm that the card deck behaves like a queue
        Assertions.assertEquals(c4, d.getDeck().getLast());
    }
    @Test
    public void dequeueTest(){
        // creates initial cards and 2 decks
        Card c1 = new Card(5);
        Card c2 = new Card(6);
        Card c3 = new Card(7);
        Card c4 = new Card(8);
        CardDeck d1 = new CardDeck();
        CardDeck d2 = new CardDeck();
        // adds cards to d1 deck and d2 deck
        // d1 deck has 4 cards while d2 deck has 3 cards to show d2 after the front value has been removed
        d1.enqueue(c1);
        d1.enqueue(c2);
        d1.enqueue(c3);
        d1.enqueue(c4);
        d2.enqueue(c2);
        d2.enqueue(c3);
        d2.enqueue(c4);
        // d1 has the dequeue method called
        d1.dequeue();
        // checks to confirm the contents of each deck are the same
        Assertions.assertEquals(d1.getDeck(),d2.getDeck());



    }

}