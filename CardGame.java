import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.lang.NumberFormatException;
import java.util.ArrayList;
class CardGame {
    public static void main(String[] args)
    {
        initialise();

    }

    public static void initialise() {
        int playerNum = playerInput();
        packInput(playerNum);
        Player.deal(Card.pack);

    }
    public static int playerInput(){
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
            CardDeck deck = new CardDeck();
        }
        return playerNum;

    }

    public static void packInput(int playerNum){
        Scanner scanner = new Scanner(System.in);
        boolean validPack = false;

        // defines outerloop so continue can be used in nested loops
        outerloop:
        while (!validPack) {
            System.out.println("Please enter location of pack to load:");
            File packFile = new File(scanner.next());
            if (!(packFile.getName().endsWith(".txt"))) {
                System.out.println("Invalid file format, pack must be a text file \n");
                continue;
            }

            //buffered reader more efficient for getting num of rows
            // Checks if row count is 8 * playerNum
            try (BufferedReader rowNumReader  = new BufferedReader(new FileReader(packFile))) {
                int rows = 0;
                while (rowNumReader.readLine() != null) rows++;
                rowNumReader.close();

                if (rows !=  8*playerNum) {
                    System.out.println("Input pack must have 8* "+playerNum+"rows");
                    continue;
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found \n");}
            catch (IOException e) {
                System.out.println("Error reading file \n");
            }

            // validates non-negatives and one int per row
            try (Scanner fileReader = new Scanner(packFile)) {

               while (fileReader.hasNextLine()) {
                    int nextVal = Integer.parseInt(fileReader.nextLine());

                    if (nextVal <0){
                        System.out.println("Pack values must be non-negative \n");
                        continue outerloop;
                    }
                    Card card = new Card(nextVal);
                    }
               validPack = true;
               System.out.println("Valid pack");
            } catch (FileNotFoundException e) {
                System.out.println("File not found \n");
                continue;
            }
                catch (NumberFormatException e) {
                System.out.println("Multiple integers on the same row, invalid pack file \n");
                continue;
            } }
        }
    }

