package Laborator41;

public class Program1 {
    public static void main(String[] args){
        String label1="test";
        String label2="test";
        String label3 = new String("test");
        String label4=new String("test");
        System.out.println(label1==label3);
        System.out.println(label1==label2);
        System.out.println(label1.equals(label3));
    }
}
