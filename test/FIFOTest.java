
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class FIFOTest {
    @Test
    public void enqueueTest(){
        Card c1 = new Card(5);
        Card c2 = new Card(6);
        Card c3 = new Card(7);
        Card c4 = new Card(8);
        CardDeck d = new CardDeck();
        d.enqueue(c1);
        d.enqueue(c2);
        d.enqueue(c3);
        d.enqueue(c4);
        Assertions.assertEquals(c4, d.getDeck().getLast());
        Assertions.assertEquals(c1,d.getDeck().getFirst());
    }
    @Test
    public void dequeueTest(){
        Card c1 = new Card(5);
        Card c2 = new Card(6);
        Card c3 = new Card(7);
        Card c4 = new Card(8);
        CardDeck d1 = new CardDeck();
        CardDeck d2 = new CardDeck();
        d1.enqueue(c1);
        d1.enqueue(c2);
        d1.enqueue(c3);
        d1.enqueue(c4);
        d2.enqueue(c2);
        d2.enqueue(c3);
        d2.enqueue(c4);
        d1.dequeue();
        Assertions.assertEquals(d1.getDeck(),d2.getDeck());



    }

}