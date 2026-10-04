import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
class cardGame {
    public static void main(String[] args)
    {
        // gets input pack
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players:");
        int playerNum = scanner.nextInt();
        // exception handling when num < 0  here
        System.out.println("Please enter location of pack to load:");
        File inputPack = new File(scanner.next());
        // exception handling for file here
        // unpack file into card objects
        try (Scanner fileReader = new Scanner(inputPack) ) {
            ArrayList<Card> pack = new ArrayList<Card>();
            while  (fileReader.hasNextLine()) {
            Card card = new Card(Integer.parseInt( fileReader.nextLine() ));
            pack.add(card);
        }}
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}