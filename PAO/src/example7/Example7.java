package example7;

public class Example7 {
    public static void main(String[] args){
        Car car=new Car();
        car.setModel("Tesla");
        car.setOwner("Larisa");
        car.setYear(2020);
        car.crash();
        System.out.println(car);
        Car car2=new Car("Ford","Ionescu",2021);
    }
}
