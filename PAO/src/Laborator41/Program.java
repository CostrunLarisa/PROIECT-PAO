package Laborator41;

public class Program {
    public static void main(String[] args){
      Player player=new Player("Alin","fotbal");
      Team team = new Team("Vlad",player);
      System.out.println(player==team.getPlayer());
      player.setSport("tenis");
        System.out.println(team.getPlayer().getSport());
    }
}
