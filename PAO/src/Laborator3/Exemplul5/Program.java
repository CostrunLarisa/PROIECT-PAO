package Laborator3.Exemplul5;

public class Program {
    public static void main(String[] args){
        Storage s=new FileStorage();
        s.save();
        s.delete();
        Storage s2=new DatabaseStorage();
        s2.save();
        s2.delete();
        s2.open();
    }
}
