import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
class CardGame {
    public static void main(String[] args)
    {
        initialise();
    }

    public static void initialise() {
        playerInput();
        packInput();

    }
    public static void playerInput(){
        boolean validPlayerNum = false;
        int playerNum = 0;
        Scanner scanner = new Scanner(System.in);
        while (!validPlayerNum) {
            System.out.println("Please enter the number of players:");
            playerNum = scanner.nextInt();
            if (playerNum < 0) {
                System.out.println("Invalid number of players, must be greater than zero");
                continue;
            }
            validPlayerNum = true;}

        for(int i = 0; i < playerNum; i++) {
            Player player = new Player();
        }

    }

    public static void packInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter location of pack to load:");
        File packFile = new File(scanner.next());

        try (Scanner fileReader = new Scanner(packFile) ) {
            while  (fileReader.hasNextLine()) {
                Card card = new Card(Integer.parseInt( fileReader.nextLine() ));
            }}
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        // pack validation 8n cards, txt file, each row contains one non-negative int
    }

}