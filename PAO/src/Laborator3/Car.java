package Laborator3;

public class Car extends Vehicle{
    String fuel;
    public Car(){
        super(null,0);
        System.out.println("Car constr no params");
    }
    public Car(String fuel,int wheelsNumber,String model)
    {
        super(model,wheelsNumber);
        this.fuel=fuel;
        System.out.println("Car constr params");

    }
    public Car(String fuel,String model)
    {
        super(model,4);
        this.fuel=fuel;
        System.out.println("Car constr 2 params");

    }
    @Override
    protected void stop(){
        System.out.println("Car stopped");
        super.start();
    }
}
