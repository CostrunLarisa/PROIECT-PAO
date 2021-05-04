package Laborator2;

public class Exemplu2 {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        Integer x = Integer.valueOf(a);
        Integer y = Integer.valueOf(b);
        Integer p = new Integer(a);
        Integer q = new Integer(b);
        }
        public static void compare(int a,int b){
        System.out.println("int==int:"+(a==b));
        }
}
