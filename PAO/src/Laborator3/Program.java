package Laborator3;
import java.util.Locale;

public class Program {
    public static void main(String[] args){
        Car car1=new Car();
        Car car2=new Car("Opel",5,"benzina");
        Bike bike1=new Bike();
        Bike bike2=new Bike("Modern","Lily");
        System.out.println("model:"+car1.model +"fuel:"+car1.fuel+" wheels:"+car1.wheelsNumber);
        car1.start();
        car1.stop();
        car1.info();
        System.out.println(car1 instanceof Vehicle);
        car2.stop();
    }
}
