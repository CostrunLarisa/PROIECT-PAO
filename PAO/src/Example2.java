import java.util.Locale;

public class Example2 {
    public static void main(String[] args){
        int x=20;
        Integer y=x;
        int z=y.intValue();
        Double a =6.89;
        int counter=5;
        incrementt(counter);
        Integer counter2=8;
        System.out.println(counter);
        double b=a.doubleValue();
        int c=Integer.parseInt("56");
        incrementWrapper(counter2);
        System.out.println(counter2);
        System.out.println(Integer.toString(33).length());
        String cc="bianca";
        cc=transform(cc);
        System.out.println(cc);
        nrPare();
    }
    public static void incrementt(int i ){
        i++;

    }
    public static void incrementWrapper(Integer i ){
        i=Integer.valueOf(i++);
    }
    public static String transform(String ch){
        return ch.toUpperCase();
    }

    public static void nrPare(){
        for(int i =1;i<=20;i++)
        {
            if(i%2==0)
            {
                System.out.println(i);
            }
        }
    }
}

