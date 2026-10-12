
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class FIFOTest {
    @Test
    public void enqueueTest(){
        Card c = new Card();
        CardDeck d = new CardDeck();
        d.enqueue(c);
        Assertions.assertEquals(c, d.getDeck());
    }
    @Test
    public void dequeueTest(){

    }

}