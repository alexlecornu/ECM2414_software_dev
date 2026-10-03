import java.util.ArrayList

public Class Player{
    ArrayList<Card> hand = new ArrayList<Card>;
    private String name;
    private int number;
    private static int num_of_players = 0;

    Player(String name, int number){
        this.name = name;
        this.number = ++num_of_players;
    }

    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    public void setName(String name){
        this.name = name;
    }
}


