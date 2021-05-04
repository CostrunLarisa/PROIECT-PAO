package Laborator41;

public class Player {
    String name;
    String sport;
    public Player(String name,String sport)
    {
        this.name=name;
        this.sport=sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
    public String getSport(){
        return this.sport;
    }
}
