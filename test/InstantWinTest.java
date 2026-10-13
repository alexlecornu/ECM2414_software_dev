import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class InstantWinTest {
    // this test is used to determine if a player can win instantly by dealing them 4 cards with the same numbers
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
        String s = "";
        // check the output file
        File f = new File("player1_output.txt");
        try(Scanner myReader = new Scanner(f)){
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                s = s + data + "\n";
            }
        } catch (FileNotFoundException e){

        }
        Assertions.assertEquals("player 1 wins\n" + "player 1 exits\n" + "player 1 hand: 1 1 1 1\n",s);
    }

    @Test
    public void instantWinTest2(){
        // this test is used to test if the player still wins even with a hand full of cards which are not their number

        Player p2 = new Player();
        Card c1 = new Card(3);
        Card c2 = new Card(3);
        Card c3 = new Card(3);
        Card c4 = new Card(3);
        // assigns all of the cards to the players hand
        p2.addCard(c1);
        p2.addCard(c2);
        p2.addCard(c3);
        p2.addCard(c4);
        p2.setBarrier();
        // starts the player thread
        p2.start();
        // check the output file
        String s = "";
        File f = new File("player1_output.txt");
        try(Scanner myReader = new Scanner(f)){
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                s = s + data + "\n";
            }
        } catch (FileNotFoundException e){

        }
        Assertions.assertEquals("player 1 wins\n" + "player 1 exits\n" + "player 1 hand: 3 3 3 3\n",s);
    }
}
